package pl.dawidh.pierce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.AttributeDto;
import pl.dawidh.pierce.controller.dto.AttributeTranslationDto;
import pl.dawidh.pierce.controller.dto.LanguageDto;
import pl.dawidh.pierce.enums.DataFileTypeEnum;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static pl.dawidh.pierce.utils.CsvUtils.getCsvParser;
import static pl.dawidh.pierce.utils.CsvUtils.getCsvReader;

@Service
public class ImportFile {
    private static final Logger log = LoggerFactory.getLogger(ImportFile.class);
    private final String noFileMassage = "No file '%s'";
    private final String savedNewLanguageMassage = "New language saved %s";
    private final String savedNewAttributeMassage = "New attribute saved %s";
    private final String savedNewAttributeTranslationMassage = "New attribute translation saved %s";
    private final List<String> ignoredWords = Arrays.asList("attribute", "sort_order", "code");
    private final AttributeService attributeService;
    private final AttributeTranslationService attributeTranslationService;
    private final LanguageService languageService;
    private final OptionService optionService;
    private final OptionTranslationService optionTranslationService;

    @Value("${import.file.separator}")
    private char splitSeparator;

    @Value("${import.file.path}")
    private String filePath;

    @Value("${import.file.files}")
    private String fileNames;

    public ImportFile(AttributeService attributeService,
                      AttributeTranslationService attributeTranslationService,
                      LanguageService languageService,
                      OptionService optionService,
                      OptionTranslationService optionTranslationService) {
        this.attributeService = attributeService;
        this.attributeTranslationService = attributeTranslationService;
        this.languageService = languageService;
        this.optionService = optionService;
        this.optionTranslationService = optionTranslationService;
    }

    public void runFileImport(){
        Arrays.stream(fileNames.split(String.valueOf(splitSeparator)))
                .map(fileName -> filePath + fileName)
                .collect(Collectors.toList())
                .forEach(e -> {
                    var file = new File(e);
                    if(file.exists()){
                        processImportedFile(file);
                    } else {
                        log.info(String.format(noFileMassage, file));
                    }
                });
    }

    private void processImportedFile(File file){
        try {
            extractData(file);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void extractData(File file) throws IOException {
        AtomicInteger lineNumbers = new AtomicInteger();
        var reader = getCsvReader(new FileReader(file), getCsvParser(splitSeparator, false), 0);
        var languages = new ArrayList<LanguageDto>();
        var headers = new ArrayList<String>();
        var loadedLine = new ArrayList<String>();
        reader.forEach(line -> {
            lineNumbers.addAndGet(1);
            if(lineNumbers.get()==1){
                headers.addAll(Arrays.asList(line));
                languages.addAll(findAndAddNewLanguage(headers));
            } else {
                loadedLine.addAll(Arrays.asList(line));
                addNewDataByFile(file.getName(), loadedLine, languages, headers);
                loadedLine.clear();
            }

        });
    }

    private List<LanguageDto> findAndAddNewLanguage(Collection<String> newData){
        var existingLanguages = languageService.getLanguages();
        var existingLanguagesCode = existingLanguages.stream()
                .map(LanguageDto::getCode)
                .collect(Collectors.toList());

        newData.stream()
                .filter(e -> !ignoredWords.contains(e) && !existingLanguagesCode.contains(e))
                .forEach(e -> {
                    var newLanguage = new LanguageDto(e);
                    newLanguage = languageService.saveLanguage(newLanguage);
                    existingLanguages.add(newLanguage);
                    log.info(String.format(savedNewLanguageMassage, newLanguage.newRecordToString()));
                });
        return existingLanguages;
    }

    private void addNewDataByFile(String filename, List<String> data, List<LanguageDto> languages, List<String> headers){
        AtomicInteger i = new AtomicInteger();
        var existingAttributes = new ArrayList<AttributeDto>();
        var dataType = DataFileTypeEnum.getDataTypeField(filename);
        if(dataType == DataFileTypeEnum.ATTRIBUTES){
            existingAttributes.addAll(findAndAddNewAttributes(data.get(0)));
            data.forEach(e -> {
                if(i.get() != 0){
                    var language = languages.stream()
                            .filter(lang -> lang.getCode().equals(headers.get(i.get())))
                            .findFirst();
                    var attribute = existingAttributes.stream()
                            .filter(attri -> attri.getCode().equals(data.get(0)))
                            .findFirst();
                    findAndAddAttributeTranslations(language.get(), attribute.get(), e);
                }
                i.addAndGet(1);
            });
        }

    }

    private List<AttributeDto> findAndAddNewAttributes(String newData){
        var existingAttributes = attributeService.getAttributes();
        var existingAttributesCode = existingAttributes.stream()
                .map(AttributeDto::getCode)
                .collect(Collectors.toList());

        if(!existingAttributesCode.contains(newData)){
            var newAttribute = new AttributeDto(newData);
            newAttribute = attributeService.saveAttribute(newAttribute);
            existingAttributes.add(newAttribute);
            log.info(String.format(savedNewAttributeMassage, newAttribute.newRecordToString()));
        }
        return existingAttributes;
    }

    private void findAndAddAttributeTranslations(LanguageDto language, AttributeDto attribute, String translation){
        var newAttributeTranslation = new AttributeTranslationDto(language.getId(), attribute.getId(), translation);
        newAttributeTranslation = attributeTranslationService.saveAttributeTranslation(newAttributeTranslation);
        log.info(String.format(savedNewAttributeTranslationMassage, newAttributeTranslation.newRecordToString()));
    }
}

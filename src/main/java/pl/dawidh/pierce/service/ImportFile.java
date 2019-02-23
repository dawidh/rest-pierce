package pl.dawidh.pierce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.*;
import pl.dawidh.pierce.enums.DataFileTypeEnum;
import pl.dawidh.pierce.exception.NotFoundException;

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
    private final String savedNewLanguageMassage = "A new language has been saved %s";
    private final String attributeCode = "attribute";
    private final String codeCode = "code";
    private final String sortOrderCode = "sort_order";
    private final List<String> ignoredWords = Arrays.asList(attributeCode, sortOrderCode, codeCode);
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
        } finally {
            if(file.delete()){
                var fileDeleteMassage = "File '%s' deleted";
                log.info(String.format(fileDeleteMassage, file.getName()));
            } else {
                var fileDeleteMassage = "Delete '%s' file operation is failed";
                log.info(String.format(fileDeleteMassage, file.getName()));
            }
        }
    }

    private void extractData(File file) throws IOException {
        var lineNumbers = new AtomicInteger();
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
        var dataType = DataFileTypeEnum.getDataTypeField(filename);
        if(dataType == DataFileTypeEnum.ATTRIBUTES){
            processAttributesFile(data, languages, headers);
        } else if (dataType == DataFileTypeEnum.OPTIONS){
            processOptionsFile(data, languages, headers);
        } else {
            throw new IllegalArgumentException(dataType.toString());
        }
    }

    private void processOptionsFile(List<String> data, List<LanguageDto> languages, List<String> headers){
        var attributeIndex = headers.indexOf(this.attributeCode);
        var optionIndex = headers.indexOf(this.codeCode);
        var sortOrderIndex = headers.indexOf(this.sortOrderCode);
        var attributeCode = data.get(attributeIndex);
        var optionCode = data.get(optionIndex);
        var sortOrder = Integer.parseInt(data.get(sortOrderIndex));
        var existingAttributes = findAndAddNewAttributes(attributeCode);
        var currentAttribute = existingAttributes.stream()
                .filter(attribute -> attribute.getCode().equals(attributeCode))
                .findFirst()
                .orElseThrow(() -> {
                    throw new NotFoundException();
                });
        var existingOptions = findAndAddNewOptions(new OptionDto(optionCode, currentAttribute.getId(), sortOrder));
        var i = new AtomicInteger();
        data.forEach(e -> {
            if(i.get() != 0 && i.get() < (data.size() - 2)){
                var languageDto = findLanguage(languages, headers.get(i.get()));
                var optionDto = findOption(existingOptions, optionCode, currentAttribute.getId()) ;
                findAndAddOptionTranslations(languageDto, optionDto, e);
            }
            i.addAndGet(1);
        });
    }

    private void processAttributesFile(List<String> data, List<LanguageDto> languages, List<String> headers){
        var attributeIndex = headers.indexOf(this.codeCode);
        var existingAttributes = findAndAddNewAttributes(data.get(attributeIndex));
        var i = new AtomicInteger();
        data.forEach(e -> {
            if(i.get() != 0) {
                var languageDto = findLanguage(languages, headers.get(i.get()));
                var attributeDto = findAttribute(existingAttributes, data.get(attributeIndex));
                findAndAddAttributeTranslations(languageDto, attributeDto, e);
            }
            i.addAndGet(1);
        });
    }

    private OptionDto findOption(List<OptionDto> options, String optionCode, Long attributeId){
        return options.stream()
                .filter(option -> option.getCode().equals(optionCode) && option.getAttributeId().equals(attributeId))
                .findFirst().orElseThrow(() -> {
                    var notFoundMassage = "Option '%s' with attribute_id '%d' not found";
                    throw new NotFoundException(String.format(notFoundMassage, optionCode, attributeId));
                });
    }

    private LanguageDto findLanguage(List<LanguageDto> languages, String languageCode){
        return languages.stream()
                .filter(language -> language.getCode().equals(languageCode))
                .findFirst().orElseThrow(() -> {
                    var notFoundMassage = "Language '%s' not found";
                    throw new NotFoundException(String.format(notFoundMassage, languageCode));
                });
    }

    private AttributeDto findAttribute(List<AttributeDto> attributes, String attributeCode){
        return attributes.stream()
                .filter(attribute -> attribute.getCode().equals(attributeCode))
                .findFirst().orElseThrow(() -> {
                    var notFoundMassage = "Attribute '%s' not found";
                    throw new NotFoundException(String.format(notFoundMassage, attributeCode));
                });
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
            var savedNewAttributeMassage = "A new attribute has been saved %s";
            log.info(String.format(savedNewAttributeMassage, newAttribute.newRecordToString()));
        }
        return existingAttributes;
    }

    private void findAndAddAttributeTranslations(LanguageDto language, AttributeDto attribute, String translation){
        var newAttributeTranslation = new AttributeTranslationDto(language.getId(), attribute.getId(), translation);
        newAttributeTranslation = attributeTranslationService.saveAttributeTranslation(newAttributeTranslation);
        var savedNewAttributeTranslationMassage = "A new translation has been saved for the '%s' attribute: %s";
        log.info(String.format(savedNewAttributeTranslationMassage, attribute.getCode(), newAttributeTranslation.newRecordToString()));
    }

    private List<OptionDto> findAndAddNewOptions(OptionDto option){
        var existingOptions = optionService.getOptions();
        var optionExist = existingOptions.stream()
                .anyMatch(existingOption -> option.getCode().equals(existingOption.getCode()) &&
                        option.getAttributeId().equals(existingOption.getAttributeId()));

        if(!optionExist){
            var newOption = optionService.saveOption(option);
            existingOptions.add(newOption);
            var savedNewOptionMassage = "A new option has been saved %s";
            log.info(String.format(savedNewOptionMassage, newOption.newRecordToString()));
        }
        return existingOptions;
    }

    private void findAndAddOptionTranslations(LanguageDto language, OptionDto option, String translation){
        var newOptionTranslation = new OptionTranslationDto(language.getId(), option.getId(), translation);
        newOptionTranslation = optionTranslationService.saveOptionTranslation(newOptionTranslation);
        var savedNewOptionTranslationMassage = "A new translation has been saved for the '%s' option: %s";
        log.info(String.format(savedNewOptionTranslationMassage, option.getCode(), newOptionTranslation.newRecordToString()));
    }
}

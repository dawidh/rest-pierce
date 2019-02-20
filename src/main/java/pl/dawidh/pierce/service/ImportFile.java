package pl.dawidh.pierce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.LanguageDto;

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
        var headers = new ArrayList<String>();
        var loadedLine = new ArrayList<>();
        reader.forEach(line -> {
            lineNumbers.addAndGet(1);
            if(lineNumbers.get()==1){
                headers.addAll(Arrays.asList(line));
                findAndAddNewLanguage(headers);
            } else {
                loadedLine.addAll(Arrays.asList(line));

                loadedLine.clear();
            }

        });
    }

    private void findAndAddNewLanguage(Collection<String> newData){
        var existingLanguages = languageService.getLanguages().stream()
                .map(LanguageDto::getCode)
                .collect(Collectors.toList());

        newData.stream()
                .filter(e -> !ignoredWords.contains(e) && !existingLanguages.contains(e))
                .forEach(e -> {
                    var newLanguage = new LanguageDto(e);
                    newLanguage = languageService.saveLanguage(newLanguage);
                    log.info(String.format(savedNewLanguageMassage, newLanguage.toStringForNewRecord()));
                });
    }
}

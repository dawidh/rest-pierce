package pl.dawidh.pierce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ImportFile {
    private static final Logger log = LoggerFactory.getLogger(ImportFile.class);
    private final String splitSeparator = ",";
    private final AttributeService attributeService;
    private final AttributeTranslationService attributeTranslationService;
    private final LanguageService languageService;
    private final OptionService optionService;
    private final OptionTranslationService optionTranslationService;

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
        var fileNames = Arrays.stream(this.fileNames.split(splitSeparator))
                .collect(Collectors.toList());
        fileNames.forEach(fileName -> processImportedFile(new File(filePath+fileName)));
    }

    private void processImportedFile(File file){

    }
}

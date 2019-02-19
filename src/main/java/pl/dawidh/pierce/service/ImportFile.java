package pl.dawidh.pierce.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImportFile {
    private static final Logger log = LoggerFactory.getLogger(ImportFile.class);
    private final String noFileMassage= "No file '%s'";
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
                .forEach(file -> {
                    if(new File(file).exists()){
                        processImportedFile(new File(file));
                    } else {
                        log.info(String.format(noFileMassage, file));
                    }
                });
    }

    private void processImportedFile(File file){
        try {
            test(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void test(File file) throws IOException {
        var reader = getCsvReader(new FileReader(file), getCsvParser(splitSeparator));
        var loadedLine = new ArrayList<>();
        reader.forEach(line -> {
            loadedLine.addAll(Arrays.asList(line));
            log.info(loadedLine.toString());
            loadedLine.clear();
        });
    }

    private CSVParser getCsvParser(char splitSeparator){
        return new CSVParserBuilder()
                .withSeparator(splitSeparator)
                .withIgnoreQuotations(true)
                .build();
    }

    private CSVReader getCsvReader(FileReader reader, CSVParser parser){
        return new CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();
    }

}

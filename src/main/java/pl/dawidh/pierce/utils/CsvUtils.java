package pl.dawidh.pierce.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;

public class CsvUtils {
    public static CSVParser getCsvParser(char splitSeparator, boolean ignoreQuotations){
        return new CSVParserBuilder()
                .withSeparator(splitSeparator)
                .withIgnoreQuotations(ignoreQuotations)
                .build();
    }

    public static CSVReader getCsvReader(FileReader reader, CSVParser parser, int skipLines){
        return new CSVReaderBuilder(reader)
                .withSkipLines(skipLines)
                .withCSVParser(parser)
                .build();
    }
}

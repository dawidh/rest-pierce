package pl.dawidh.pierce.enums;

import java.util.Arrays;

public enum DataFileTypeEnum {
    ATTRIBUTES("attributes.csv"),
    OPTIONS("options.csv");

    private String fileData;
    private static final String ERROR_MASSAGE = "Data type \'%s\' not found.";

    DataFileTypeEnum(String fileData){
        this.fileData = fileData;
    }

    public static DataFileTypeEnum getDataTypeField(String fileData) {
        return Arrays.stream(values())
                .filter(guestEnum -> guestEnum.fileData.equals(fileData))
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(String.format(ERROR_MASSAGE, fileData));
                });
    }
}

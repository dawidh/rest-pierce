package pl.dawidh.pierce.controller.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

public class AttributeDto extends BaseData {
    @NotNull
    private String code;

    private Collection<AttributeTranslationDto> attributeTranslations;

    public AttributeDto() {
    }

    public AttributeDto(@NotNull String code) {
        this.code = code;
    }

    public AttributeDto(Long id,
                        LocalDateTime created,
                        LocalDateTime modified,
                        @NotNull String code) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.code = code;
    }

    public AttributeDto(Long id,
                        LocalDateTime created,
                        LocalDateTime modified,
                        @NotNull String code,
                        Collection<AttributeTranslationDto> attributeTranslations) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.code = code;
        this.attributeTranslations = attributeTranslations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection<AttributeTranslationDto> getAttributeTranslations() {
        return attributeTranslations;
    }

    public String newRecordToString() {
        return String.format("'%s', id = '%d'", code, id);
    }
}

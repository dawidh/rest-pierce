package pl.dawidh.pierce.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

@JsonIgnoreProperties(value={ "AttributeTranslationDto"}, allowSetters= true)
public class LanguageDto extends BaseData {
    @NotNull
    private String code;

    @JsonProperty("AttributeTranslationDto")
    private Collection<AttributeTranslationDto> attributeTranslations;

    public LanguageDto(@NotNull String code) {
        this.code = code;
    }

    public LanguageDto(Long id,
                       LocalDateTime created,
                       LocalDateTime modified,
                       @NotNull String code) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.code = code;
    }

    public LanguageDto(Long id,
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

    public void setAttributeTranslations(Collection<AttributeTranslationDto> attributeTranslations) {
        this.attributeTranslations = attributeTranslations;
    }

    public String newRecordToString() {
        return String.format("'%s', id = {%d}", code, id);
    }
}

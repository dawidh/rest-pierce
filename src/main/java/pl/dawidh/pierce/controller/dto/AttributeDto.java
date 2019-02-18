package pl.dawidh.pierce.controller.dto;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public class AttributeDto extends BaseData {
    @NotNull
    private String code;

    private Collection<AttributeTranslationDto> attributeTranslations;

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
}

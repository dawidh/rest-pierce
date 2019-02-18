package pl.dawidh.pierce.controller.dto;

import javax.validation.constraints.NotNull;

public class AttributeTranslationDto extends BaseData {
    @NotNull
    private Long languageId;

    @NotNull
    private Long attributeId;

    @NotNull
    private String translate;

    public Long getLanguage() {
        return languageId;
    }

    public void setLanguage(Long languageId) {
        this.languageId = languageId;
    }

    public Long getAttribute() {
        return attributeId;
    }

    public void setAttribute(Long attributeId) {
        this.attributeId = attributeId;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}

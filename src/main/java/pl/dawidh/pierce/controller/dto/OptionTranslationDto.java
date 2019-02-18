package pl.dawidh.pierce.controller.dto;

import javax.validation.constraints.NotNull;

public class OptionTranslationDto extends BaseData {
    @NotNull
    private Long languageId;

    @NotNull
    private Long optionId;

    @NotNull
    private String translate;

    public Long getLanguage() {
        return languageId;
    }

    public void setLanguage(Long languageId) {
        this.languageId = languageId;
    }

    public Long getOption() {
        return optionId;
    }

    public void setOption(Long optionId) {
        this.optionId = optionId;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}

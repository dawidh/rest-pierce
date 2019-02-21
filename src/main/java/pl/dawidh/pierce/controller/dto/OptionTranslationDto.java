package pl.dawidh.pierce.controller.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OptionTranslationDto extends BaseData {
    @NotNull
    private Long languageId;

    @NotNull
    private Long optionId;

    @NotNull
    private String translate;

    public OptionTranslationDto(Long id,
                                   LocalDateTime created,
                                   LocalDateTime modified,
                                   @NotNull Long languageId,
                                   @NotNull Long optionId,
                                   @NotNull String translate) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.languageId = languageId;
        this.optionId = optionId;
        this.translate = translate;
    }

    public OptionTranslationDto(@NotNull Long languageId,
                                   @NotNull Long optionId,
                                   @NotNull String translate) {
        this.languageId = languageId;
        this.optionId = optionId;
        this.translate = translate;
    }

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

    public String newRecordToString() {
        return String.format("'%s', id = '%d'", translate, id);
    }
}

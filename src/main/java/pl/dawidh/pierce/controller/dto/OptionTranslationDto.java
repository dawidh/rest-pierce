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

    public OptionTranslationDto() {
    }

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

    public Long getLanguageId() {
        return languageId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public String getTranslate() {
        return translate;
    }

    public String newRecordToString() {
        return String.format("'%s', id = '%d'", translate, id);
    }
}

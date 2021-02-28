package pl.dawidh.pierce.controller.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AttributeTranslationDto extends BaseData {
    @NotNull
    private Long languageId;

    @NotNull
    private Long attributeId;

    @NotNull
    private String translate;

    public AttributeTranslationDto() {
    }

    public AttributeTranslationDto(Long id,
                                   LocalDateTime created,
                                   LocalDateTime modified,
                                   @NotNull Long languageId,
                                   @NotNull Long attributeId,
                                   @NotNull String translate) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.languageId = languageId;
        this.attributeId = attributeId;
        this.translate = translate;
    }

    public AttributeTranslationDto(@NotNull Long languageId,
                                   @NotNull Long attributeId,
                                   @NotNull String translate) {
        this.languageId = languageId;
        this.attributeId = attributeId;
        this.translate = translate;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public String getTranslate() {
        return translate;
    }

    public String newRecordToString() {
        return String.format("'%s', id = '%d'", translate, id);
    }
}

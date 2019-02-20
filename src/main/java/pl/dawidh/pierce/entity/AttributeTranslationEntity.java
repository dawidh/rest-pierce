package pl.dawidh.pierce.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "attribute_translations")
public class AttributeTranslationEntity extends BaseData {
    @ManyToOne
    @NotNull
    @JoinColumn(name = "language_id")
    private LanguageEntity language;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "attribute_id")
    private AttributeEntity attribute;

    @NotNull
    private String translate;

    public AttributeTranslationEntity(@NotNull String translate) {
        this.translate = translate;
    }

    public AttributeTranslationEntity(@NotNull LanguageEntity language, @NotNull AttributeEntity attribute, @NotNull String translate) {
        this.language = language;
        this.attribute = attribute;
        this.translate = translate;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public AttributeEntity getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeEntity attribute) {
        this.attribute = attribute;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}

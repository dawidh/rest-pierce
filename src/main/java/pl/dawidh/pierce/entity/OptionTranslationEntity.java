package pl.dawidh.pierce.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "attributes")
public class OptionTranslationEntity extends BaseData {
    @ManyToOne
    @NotNull
    @JoinColumn(name = "language_id")
    private LanguageEntity language;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "option_id")
    private OptionEntity option;

    @NotNull
    private String translate;

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public OptionEntity getOption() {
        return option;
    }

    public void setOption(OptionEntity option) {
        this.option = option;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}

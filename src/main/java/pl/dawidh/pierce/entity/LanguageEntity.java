package pl.dawidh.pierce.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "languages")
public class LanguageEntity extends BaseData {
    @NotNull
    private String code;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    private Collection<AttributeTranslationEntity> attributeTranslations;

    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    private Collection<OptionTranslationEntity> optionTranslations;

    public LanguageEntity() {
    }

    public LanguageEntity(Long id) {
        this.id = id;
    }

    public LanguageEntity(@NotNull String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

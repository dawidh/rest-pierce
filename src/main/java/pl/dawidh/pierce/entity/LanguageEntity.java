package pl.dawidh.pierce.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "languages")
public class LanguageEntity extends BaseData {
    @NotNull
    private String code;

    @OneToMany(mappedBy = "language", fetch = FetchType.EAGER)
    private Collection<AttributeTranslationEntity> attributeTranslations;

    public LanguageEntity() {
    }

    public LanguageEntity(Long id) {
        this.id = id;
    }

    public LanguageEntity(@NotNull String code) {
        this.code = code;
    }

    public LanguageEntity(@NotNull String code, Collection<AttributeTranslationEntity> attributeTranslations) {
        this.code = code;
        this.attributeTranslations = attributeTranslations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection<AttributeTranslationEntity> getAttributeTranslations() {
        return attributeTranslations;
    }

    public void setAttributeTranslations(Collection<AttributeTranslationEntity> attributeTranslations) {
        this.attributeTranslations = attributeTranslations;
    }
}

package pl.dawidh.pierce.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "attributes")
public class AttributeEntity extends BaseData {
    @NotNull
    private String code;

    @OneToMany(mappedBy = "attribute", fetch = FetchType.EAGER)
    private Collection<AttributeTranslationEntity> attributeTranslations;

    public AttributeEntity() {
    }

    public AttributeEntity(@NotNull String code) {
        this.code = code;
    }

    public AttributeEntity(@NotNull String code, Collection<AttributeTranslationEntity> attributeTranslations) {
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

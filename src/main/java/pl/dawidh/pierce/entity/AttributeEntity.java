package pl.dawidh.pierce.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "attributes")
public class AttributeEntity extends BaseData {
    @NotNull
    private String code;

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL)
    private Collection<AttributeTranslationEntity> attributeTranslations;


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

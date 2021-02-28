package pl.dawidh.pierce.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "attributes")
public class AttributeEntity extends BaseData {
    @NotNull
    private String code;

    @OneToMany(mappedBy = "attribute")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<AttributeTranslationEntity> attributeTranslations;

    public AttributeEntity() {
    }

    public AttributeEntity(Long id) {
        this.id = id;
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
}

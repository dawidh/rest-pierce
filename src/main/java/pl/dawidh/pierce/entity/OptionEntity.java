package pl.dawidh.pierce.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "options")
public class OptionEntity extends BaseData{
    @NotNull
    private String code;

    @OneToOne
    @JoinColumn(name = "attribute_id")
    private AttributeEntity attribute;

    @NotNull
    @Column(name = "sort_order")
    private Integer sortOrder;

    @OneToMany(mappedBy = "option")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<OptionTranslationEntity> optionTranslations;

    public OptionEntity() {
    }

    public OptionEntity(Long id) {
        this.id = id;
    }

    public OptionEntity(@NotNull String code) {
        this.code = code;
    }

    public OptionEntity(@NotNull String code,
                        AttributeEntity attribute,
                        @NotNull Integer sortOrder) {
        this.code = code;
        this.attribute = attribute;
        this.sortOrder = sortOrder;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AttributeEntity getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeEntity attribute) {
        this.attribute = attribute;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}

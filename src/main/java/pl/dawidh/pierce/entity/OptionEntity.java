package pl.dawidh.pierce.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

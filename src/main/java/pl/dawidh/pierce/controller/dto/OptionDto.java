package pl.dawidh.pierce.controller.dto;

import javax.validation.constraints.NotNull;

public class OptionDto extends BaseData {
    @NotNull
    private String code;

    @NotNull
    private Long attributeId;

    @NotNull
    private Integer sortOrder;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getAttribute() {
        return attributeId;
    }

    public void setAttribute(Long attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}

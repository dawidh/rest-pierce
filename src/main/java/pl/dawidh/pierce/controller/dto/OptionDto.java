package pl.dawidh.pierce.controller.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OptionDto extends BaseData {
    @NotNull
    private String code;

    @NotNull
    private Long attributeId;

    @NotNull
    private Integer sortOrder;

    public OptionDto(@NotNull String code) {
        this.code = code;
    }

    public OptionDto(@NotNull String code,
                     @NotNull Long attributeId,
                     @NotNull Integer sortOrder) {
        this.code = code;
        this.attributeId = attributeId;
        this.sortOrder = sortOrder;
    }

    public OptionDto(Long id,
                        LocalDateTime created,
                        LocalDateTime modified,
                        @NotNull String code,
                        @NotNull Long attributeId,
                        @NotNull Integer sortOrder) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.code = code;
        this.attributeId = attributeId;
        this.sortOrder = sortOrder;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String newRecordToString() {
        return String.format("'%s', id = '%d'", code, id);
    }
}

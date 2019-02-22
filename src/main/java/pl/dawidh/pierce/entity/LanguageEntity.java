package pl.dawidh.pierce.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "languages")
public class LanguageEntity extends BaseData {
    @NotNull
    private String code;

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

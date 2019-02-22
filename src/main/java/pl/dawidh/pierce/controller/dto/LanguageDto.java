package pl.dawidh.pierce.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties(value={"AttributeTranslationDto"}, allowSetters= true)
public class LanguageDto extends BaseData {
    @NotNull
    private String code;

    public LanguageDto(@NotNull String code) {
        this.code = code;
    }

    public LanguageDto(Long id,
                       LocalDateTime created,
                       LocalDateTime modified,
                       @NotNull String code) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String newRecordToString() {
        return String.format("'%s', id = {%d}", code, id);
    }
}

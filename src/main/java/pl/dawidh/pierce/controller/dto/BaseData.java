package pl.dawidh.pierce.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public abstract class BaseData {
    protected Long id;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm")
    protected LocalDateTime created;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm")
    protected LocalDateTime modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

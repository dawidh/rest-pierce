package pl.dawidh.pierce.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public class BaseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "created__")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    @Column(name = "modified__")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modified;

    @PrePersist
    public void prePersist() {
        created = Timestamp.valueOf(LocalDateTime.now());
        modified = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    public void PreUpdate() {
        modified = Timestamp.valueOf(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }
}

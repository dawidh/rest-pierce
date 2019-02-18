package pl.dawidh.pierce.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "created__")
    protected Timestamp created;

    @Column(name = "modified__")
    protected Timestamp modified;

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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

}

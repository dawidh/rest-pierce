package pl.dawidh.pierce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawidh.pierce.entity.AttributeEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeEntity, Long> {
    List<AttributeEntity> findAllByCode(String code);
    Optional<AttributeEntity> findByCodeEqualsIgnoreCase(String code);
}

package pl.dawidh.pierce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawidh.pierce.entity.AttributeTranslationEntity;

@Repository
public interface AttributeTranslationRepository extends JpaRepository<AttributeTranslationEntity, Long> {
}

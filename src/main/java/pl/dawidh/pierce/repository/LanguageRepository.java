package pl.dawidh.pierce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawidh.pierce.entity.LanguageEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {
    List<LanguageEntity> findAllByCode(String code);
    Optional<LanguageEntity> findByCodeEqualsIgnoreCase(String code);
}

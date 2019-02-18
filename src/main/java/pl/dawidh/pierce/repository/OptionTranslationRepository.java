package pl.dawidh.pierce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawidh.pierce.entity.OptionTranslationEntity;

@Repository
public interface OptionTranslationRepository extends JpaRepository<OptionTranslationEntity, Long> {
}

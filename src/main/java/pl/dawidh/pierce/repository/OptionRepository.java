package pl.dawidh.pierce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawidh.pierce.entity.OptionEntity;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Long> {
}

package pl.dawidh.pierce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dawidh.pierce.entity.OptionTranslationEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OptionTranslationRepository extends JpaRepository<OptionTranslationEntity, Long> {
    @Query(value =
            "SELECT * FROM option_translations ot " +
            "LEFT JOIN options o on ot.option_id=o.id " +
            "WHERE lower(ot.translate)=lower(:translate) " +
            "AND lower(o.code)=lower(:optionCode);", nativeQuery = true)
    List<OptionTranslationEntity> findByTranslateAndOptionCode(@Param("translate")String translate,
                                                                     @Param("optionCode")String optionCode);

    List<OptionTranslationEntity> findByTranslateContainsIgnoreCase(String translate);

    @Query(value =
            "SELECT * FROM option_translations ot " +
            "LEFT JOIN options o on ot.option_id=o.id " +
            "WHERE lower(o.code)=lower(:optionCode);", nativeQuery = true)
    List<OptionTranslationEntity> findByOptionCode(@Param("optionCode")String optionCode);

    @Modifying
    @Transactional
    @Query(value = "delete from option_translations where option_id=:optionId", nativeQuery = true)
    void deleteAllByOption_Id(@Param("optionId")Long optionId);
}

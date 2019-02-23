package pl.dawidh.pierce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dawidh.pierce.entity.AttributeTranslationEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AttributeTranslationRepository extends JpaRepository<AttributeTranslationEntity, Long> {
    @Query(value =
            "SELECT * FROM attribute_translations at " +
            "LEFT JOIN attributes a on at.attribute_id=a.id " +
            "WHERE lower(at.translate)=lower(:translate) " +
            "AND lower(a.code)=lower(:attributeCode);", nativeQuery = true)
    List<AttributeTranslationEntity> findByTranslateAndAttributeCode(@Param("translate")String translate,
                                                                     @Param("attributeCode")String attributeCode);

    List<AttributeTranslationEntity> findByTranslateContainsIgnoreCase(String translate);

    @Query(value =
            "SELECT * FROM attribute_translations at " +
            "LEFT JOIN attributes a on at.attribute_id=a.id " +
            "WHERE lower(a.code)=lower(:attributeCode);", nativeQuery = true)
    List<AttributeTranslationEntity> findByAttributeCode(@Param("attributeCode")String attributeCode);

    @Modifying
    @Transactional
    @Query(value = "delete from attribute_translations where attribute_id=:attributeId", nativeQuery = true)
    void deleteAllByAttribute_Id(@Param("attributeId")Long attributeId);
}

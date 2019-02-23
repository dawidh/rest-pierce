package pl.dawidh.pierce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dawidh.pierce.entity.OptionEntity;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Long> {
    @Query(value =
            "SELECT * FROM options o " +
            "LEFT JOIN attributes a on o.attribute_id=a.id " +
            "WHERE lower(o.code)=lower(:code) " +
            "AND lower(a.code)=lower(:attributeCode);", nativeQuery = true)
    List<OptionEntity> findByCodeAndAttribute(@Param("code")String code,
                                              @Param("attributeCode")String attributeCode);

    @Query(value =
            "SELECT * FROM options o " +
            "LEFT JOIN attributes a on o.attribute_id=a.id " +
            "WHERE lower(o.code)=lower(:code) " +
            "AND lower(a.id)=lower(:attributeId);", nativeQuery = true)
    List<OptionEntity> findByCodeAndAttributeId(@Param("code")String code,
                                              @Param("attributeId")Long attributeId);

    List<OptionEntity> findByCodeEqualsIgnoreCase(String code);

    @Query(value =
            "SELECT * FROM options o " +
            "LEFT JOIN attributes a on o.attribute_id=a.id " +
            "WHERE lower(a.code)=lower(:attributeCode);", nativeQuery = true)
    List<OptionEntity> findByAttributeCode(@Param("attributeCode")String attributeCode);
}

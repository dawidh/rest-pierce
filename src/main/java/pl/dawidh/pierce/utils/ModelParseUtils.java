package pl.dawidh.pierce.utils;

import pl.dawidh.pierce.controller.dto.AttributeDto;
import pl.dawidh.pierce.controller.dto.AttributeTranslationDto;
import pl.dawidh.pierce.controller.dto.LanguageDto;
import pl.dawidh.pierce.entity.AttributeEntity;
import pl.dawidh.pierce.entity.AttributeTranslationEntity;
import pl.dawidh.pierce.entity.LanguageEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static pl.dawidh.pierce.utils.DateUtils.sqlTimestampToLocalDateTime;

public class ModelParseUtils {
    public static LanguageEntity languageDtoToEntity(LanguageDto dto){
        if(dto.getAttributeTranslations() == null || dto.getAttributeTranslations().isEmpty()){
            return new LanguageEntity(dto.getCode());
        } else {
            var attributes = attributeTranslationCollectionDtoToListEntity(dto.getAttributeTranslations());
            return new LanguageEntity(dto.getCode(),
                    attributes);
        }
    }

    public static List<LanguageEntity> languageCollectionDtoToListEntity(Collection<LanguageDto> dtos){
        return dtos.stream()
                .map(ModelParseUtils::languageDtoToEntity)
                .collect(Collectors.toList());
    }

    public static LanguageDto languageEntityToDto(LanguageEntity entity){
        if(entity.getAttributeTranslations() == null || entity.getAttributeTranslations().isEmpty()){
            return new LanguageDto(entity.getId(),
                    sqlTimestampToLocalDateTime(entity.getCreated()),
                    sqlTimestampToLocalDateTime(entity.getModified()),
                    entity.getCode());
        } else {
            var attributes = attributeTranslationCollectionEntityToListDto(entity.getAttributeTranslations());
            return new LanguageDto(entity.getId(),
                    sqlTimestampToLocalDateTime(entity.getCreated()),
                    sqlTimestampToLocalDateTime(entity.getModified()),
                    entity.getCode(),
                    attributes);
        }
    }

    public static List<LanguageDto> languageCollectionEntityToListDto(Collection<LanguageEntity> entities){
        return entities.stream()
                .map(ModelParseUtils::languageEntityToDto)
                .collect(Collectors.toList());
    }

    public static AttributeEntity attributeDtoToEntity(AttributeDto dto){
        if(dto.getAttributeTranslations() == null || dto.getAttributeTranslations().isEmpty()){
            return new AttributeEntity(dto.getCode());
        } else {
            var attributes = dto.getAttributeTranslations();
            return new AttributeEntity(dto.getCode(),
                    null);
        }
    }

    public static List<AttributeEntity> attributeCollectionDtoToEntityList(Collection<AttributeDto> dtos){
        return dtos.stream()
                .map(ModelParseUtils::attributeDtoToEntity)
                .collect(Collectors.toList());
    }

    public static AttributeDto attributeEntityToDto(AttributeEntity entity){
        if(entity.getAttributeTranslations() == null || entity.getAttributeTranslations().isEmpty()){
            return new AttributeDto(entity.getId(),
                    sqlTimestampToLocalDateTime(entity.getCreated()),
                    sqlTimestampToLocalDateTime(entity.getModified()),
                    entity.getCode());
        } else {
            var attributes = entity.getAttributeTranslations();
            return new AttributeDto(entity.getId(),
                    sqlTimestampToLocalDateTime(entity.getCreated()),
                    sqlTimestampToLocalDateTime(entity.getModified()),
                    entity.getCode(),
                    null);
        }
    }

    public static List<AttributeDto> attributeCollectionEntityToListDto(Collection<AttributeEntity> entities){
        return entities.stream()
                .map(ModelParseUtils::attributeEntityToDto)
                .collect(Collectors.toList());
    }

    public static AttributeTranslationEntity attributeTranslationDtoToEntity(AttributeTranslationDto dto){
        return new AttributeTranslationEntity(dto.getTranslate());
    }

    public static AttributeTranslationDto attributeTranslationEntityToDto(AttributeTranslationEntity entity){
        return new AttributeTranslationDto(entity.getLanguage().getId(),
                entity.getAttribute().getId(),
                entity.getTranslate());
    }

    public static List<AttributeTranslationEntity> attributeTranslationCollectionDtoToListEntity(Collection<AttributeTranslationDto> dtos){
        return dtos.stream()
                .map(ModelParseUtils::attributeTranslationDtoToEntity)
                .collect(Collectors.toList());
    }

    public static List<AttributeTranslationDto> attributeTranslationCollectionEntityToListDto(Collection<AttributeTranslationEntity> entities){
        return entities.stream()
                .map(ModelParseUtils::attributeTranslationEntityToDto)
                .collect(Collectors.toList());
    }
}

package pl.dawidh.pierce.utils;

import pl.dawidh.pierce.controller.dto.*;
import pl.dawidh.pierce.entity.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static pl.dawidh.pierce.utils.DateUtils.sqlTimestampToLocalDateTime;

public class ModelParseUtils {
    public static LanguageEntity languageDtoToEntity(LanguageDto dto){
        return new LanguageEntity(dto.getCode());
    }

    /*public static List<LanguageEntity> languageCollectionDtoToListEntity(Collection<LanguageDto> dtos){
        return dtos.stream()
                .map(ModelParseUtils::languageDtoToEntity)
                .collect(Collectors.toList());
    }*/

    public static LanguageDto languageEntityToDto(LanguageEntity entity){
        return new LanguageDto(entity.getId(),
                sqlTimestampToLocalDateTime(entity.getCreated()),
                sqlTimestampToLocalDateTime(entity.getModified()),
                entity.getCode());
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
            var attributes = attributeTranslationCollectionDtoToListEntity(dto.getAttributeTranslations());
            return new AttributeEntity(dto.getCode(),
                    attributes);
        }
    }

    /*public static List<AttributeEntity> attributeCollectionDtoToEntityList(Collection<AttributeDto> dtos){
        return dtos.stream()
                .map(ModelParseUtils::attributeDtoToEntity)
                .collect(Collectors.toList());
    }*/

    public static AttributeDto attributeEntityToDto(AttributeEntity entity){
        if(entity.getAttributeTranslations() == null || entity.getAttributeTranslations().isEmpty()){
            return new AttributeDto(entity.getId(),
                    sqlTimestampToLocalDateTime(entity.getCreated()),
                    sqlTimestampToLocalDateTime(entity.getModified()),
                    entity.getCode());
        } else {
            var attributes = attributeTranslationCollectionEntityToListDto(entity.getAttributeTranslations());
            return new AttributeDto(entity.getId(),
                    sqlTimestampToLocalDateTime(entity.getCreated()),
                    sqlTimestampToLocalDateTime(entity.getModified()),
                    entity.getCode(),
                    attributes);
        }
    }

    public static List<AttributeDto> attributeCollectionEntityToListDto(Collection<AttributeEntity> entities){
        return entities.stream()
                .map(ModelParseUtils::attributeEntityToDto)
                .collect(Collectors.toList());
    }

    public static AttributeTranslationEntity attributeTranslationDtoToEntity(AttributeTranslationDto dto){
        return new AttributeTranslationEntity(new LanguageEntity(dto.getLanguageId()),
                new AttributeEntity(dto.getAttributeId()),
                dto.getTranslate());
    }

    public static AttributeTranslationDto attributeTranslationEntityToDto(AttributeTranslationEntity entity){
        return new AttributeTranslationDto(entity.getId(),
                sqlTimestampToLocalDateTime(entity.getCreated()),
                sqlTimestampToLocalDateTime( entity.getModified()),
                entity.getLanguage().getId(),
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

    public static OptionEntity optionDtoToEntity(OptionDto dto){
        return new OptionEntity(dto.getCode(),
                new AttributeEntity(dto.getAttributeId()),
                dto.getSortOrder());
    }

    /*public static List<OptionEntity> optionCollectionDtoToListEntity(Collection<OptionDto> dtos){
        return dtos.stream()
                .map(ModelParseUtils::optionDtoToEntity)
                .collect(Collectors.toList());
    }*/

    public static OptionDto optionEntityToDto(OptionEntity entity){
        return new OptionDto(entity.getId(),
                sqlTimestampToLocalDateTime(entity.getCreated()),
                sqlTimestampToLocalDateTime(entity.getModified()),
                entity.getCode(),
                entity.getAttribute().getId(),
                entity.getSortOrder());
    }

    public static List<OptionDto> optionCollectionEntityToListDto(Collection<OptionEntity> entities){
        return entities.stream()
                .map(ModelParseUtils::optionEntityToDto)
                .collect(Collectors.toList());
    }

    public static OptionTranslationEntity optionTranslationDtoToEntity(OptionTranslationDto dto){
        return new OptionTranslationEntity(new LanguageEntity(dto.getLanguageId()),
                new OptionEntity(dto.getOptionId()),
                dto.getTranslate());
    }

    public static OptionTranslationDto optionTranslationEntityToDto(OptionTranslationEntity entity){
        return new OptionTranslationDto(entity.getId(),
                sqlTimestampToLocalDateTime(entity.getCreated()),
                sqlTimestampToLocalDateTime(entity.getModified()),
                entity.getLanguage().getId(),
                entity.getOption().getId(),
                entity.getTranslate());
    }

    public static List<OptionTranslationDto> optionTranslationCollectionEntityToListDto(Collection<OptionTranslationEntity> entities){
        return entities.stream()
                .map(ModelParseUtils::optionTranslationEntityToDto)
                .collect(Collectors.toList());
    }
}

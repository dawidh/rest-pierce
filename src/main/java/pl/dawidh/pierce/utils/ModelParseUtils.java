package pl.dawidh.pierce.utils;

import pl.dawidh.pierce.controller.dto.LanguageDto;
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
            var attributes = dto.getAttributeTranslations();
            return new LanguageEntity(dto.getCode(),
                    null);
        }
    }

    public static List<LanguageEntity> languageListDtoToEntity(Collection<LanguageDto> dtos){
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
            var attributes = entity.getAttributeTranslations();
            return new LanguageDto(entity.getId(),
                    sqlTimestampToLocalDateTime(entity.getCreated()),
                    sqlTimestampToLocalDateTime(entity.getModified()),
                    entity.getCode(),
                    null);
        }
    }

    public static List<LanguageDto> languageListEntityToDto(Collection<LanguageEntity> entities){
        return entities.stream()
                .map(ModelParseUtils::languageEntityToDto)
                .collect(Collectors.toList());
    }
}

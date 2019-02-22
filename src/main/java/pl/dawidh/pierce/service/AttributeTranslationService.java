package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.AttributeTranslationDto;
import pl.dawidh.pierce.entity.AttributeEntity;
import pl.dawidh.pierce.entity.LanguageEntity;
import pl.dawidh.pierce.exception.NotFoundException;
import pl.dawidh.pierce.repository.AttributeTranslationRepository;

import java.util.Collections;
import java.util.List;

import static pl.dawidh.pierce.utils.ModelParseUtils.*;

@Service
public class AttributeTranslationService {
    private final AttributeTranslationRepository attributeTranslationRepository;

    public AttributeTranslationService(AttributeTranslationRepository attributeTranslationRepository) {
        this.attributeTranslationRepository = attributeTranslationRepository;
    }

    public List<AttributeTranslationDto> getAttributeTranslations(Long id, String translation, String attributeCode){
        if(id != null){
            return Collections.singletonList(attributeTranslationEntityToDto(attributeTranslationRepository.findById(id).orElseThrow(() -> {
                var notFoundMassage = "Attribute translation with id='%d' not found";
                throw new NotFoundException(String.format(notFoundMassage, id));
            })));
        } else if (translation != null && attributeCode != null){
            return attributeTranslationCollectionEntityToListDto(attributeTranslationRepository.findByTranslateAndAttributeCode(translation, attributeCode));
        } else if (translation != null){
            return attributeTranslationCollectionEntityToListDto(attributeTranslationRepository.findByTranslateContainsIgnoreCase(translation));
        } else if (attributeCode != null){
            return attributeTranslationCollectionEntityToListDto(attributeTranslationRepository.findByAttributeCode(attributeCode));
        } else {
            return attributeTranslationCollectionEntityToListDto(attributeTranslationRepository.findAll());
        }
    }

    public AttributeTranslationDto saveAttributeTranslation(AttributeTranslationDto attributeTranslationDto){
        var newAttributeTranslation = attributeTranslationDtoToEntity(attributeTranslationDto);
        var savedAttributeTranslation = attributeTranslationRepository.save(newAttributeTranslation);
        return attributeTranslationEntityToDto(savedAttributeTranslation);
    }

    public AttributeTranslationDto putAttributeTranslation(AttributeTranslationDto newData, Long translationId){
        var translate = attributeTranslationRepository.findById(translationId)
                .map(attributeEntity -> {
                    attributeEntity.setTranslate(newData.getTranslate());
                    attributeEntity.setAttribute(new AttributeEntity(newData.getAttributeId()));
                    attributeEntity.setLanguage(new LanguageEntity(newData.getLanguageId()));
                    return attributeTranslationRepository.save(attributeEntity);
                })
                .orElseGet(() -> attributeTranslationRepository.save(attributeTranslationDtoToEntity(newData)));
        return attributeTranslationEntityToDto(translate);
    }

    public AttributeTranslationDto deleteAttributeTranslation(Long translationId){
        var attributeTranslation = attributeTranslationRepository.findById(translationId).orElseThrow(() -> {
            var notFoundMassage = "Attribute translate with id='%d' not found";
            throw new NotFoundException(String.format(notFoundMassage, translationId));
        });
        attributeTranslationRepository.delete(attributeTranslation);
        return attributeTranslationEntityToDto(attributeTranslation);
    }

}

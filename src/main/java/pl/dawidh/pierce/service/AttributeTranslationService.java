package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.AttributeTranslationDto;
import pl.dawidh.pierce.repository.AttributeTranslationRepository;

import static pl.dawidh.pierce.utils.ModelParseUtils.*;

@Service
public class AttributeTranslationService {
    private final AttributeTranslationRepository attributeTranslationRepository;

    public AttributeTranslationService(AttributeTranslationRepository attributeTranslationRepository) {
        this.attributeTranslationRepository = attributeTranslationRepository;
    }

    public AttributeTranslationDto saveAttributeTranslation(AttributeTranslationDto attributeTranslationDto){
        var newAttributeTranslation = attributeTranslationDtoToEntity(attributeTranslationDto);
        var savedAttributeTranslation = attributeTranslationRepository.save(newAttributeTranslation);
        return attributeTranslationEntityToDto(savedAttributeTranslation);
    }

    public AttributeTranslationDto putAttributeTranslation(AttributeTranslationDto attributeTranslationDto){
        return null;//TODO
    }

    public AttributeTranslationDto deleteAttributeTranslation(AttributeTranslationDto attributeTranslationDto){
        return null;//TODO
    }
}

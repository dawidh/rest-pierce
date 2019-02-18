package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.AttributeTranslationDto;
import pl.dawidh.pierce.repository.AttributeTranslationRepository;

@Service
public class AttributeTranslationService {
    private final AttributeTranslationRepository attributeTranslationRepository;

    public AttributeTranslationService(AttributeTranslationRepository attributeTranslationRepository) {
        this.attributeTranslationRepository = attributeTranslationRepository;
    }

    public AttributeTranslationDto saveAttributeTranslation(AttributeTranslationDto attributeTranslationDto){
        return null;//TODO
    }

    public AttributeTranslationDto putAttributeTranslation(AttributeTranslationDto attributeTranslationDto){
        return null;//TODO
    }

    public AttributeTranslationDto deleteAttributeTranslation(AttributeTranslationDto attributeTranslationDto){
        return null;//TODO
    }
}

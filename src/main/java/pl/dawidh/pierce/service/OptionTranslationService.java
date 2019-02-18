package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.OptionTranslationDto;
import pl.dawidh.pierce.repository.OptionTranslationRepository;

@Service
public class OptionTranslationService {
    private final OptionTranslationRepository optionTranslationRepository;

    public OptionTranslationService(OptionTranslationRepository optionTranslationRepository) {
        this.optionTranslationRepository = optionTranslationRepository;
    }

    public OptionTranslationDto saveOptionTranslation(OptionTranslationDto optionTranslationDto){
        return null;
    }

    public OptionTranslationDto putOptionTranslation(OptionTranslationDto optionTranslationDto){
        return null;
    }

    public OptionTranslationDto deleteOptionTranslation(OptionTranslationDto optionTranslationDto){
        return null;
    }
}

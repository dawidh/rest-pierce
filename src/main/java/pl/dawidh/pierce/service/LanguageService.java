package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.LanguageDto;
import pl.dawidh.pierce.repository.LanguageRepository;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public LanguageDto saveLanguage(LanguageDto languageDto){
        return null;
    }

    public LanguageDto putLanguage(LanguageDto languageDto){
        return null;
    }

    public LanguageDto deleteLanguage(LanguageDto languageDto){
        return null;
    }
}

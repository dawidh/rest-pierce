package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.LanguageDto;
import pl.dawidh.pierce.repository.LanguageRepository;

import java.util.List;

import static pl.dawidh.pierce.utils.ModelParseUtils.*;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<LanguageDto> getLanguages(){
        return languageListEntityToDto(languageRepository.findAll());
    }

    public LanguageDto saveLanguage(LanguageDto languageDto){
        var newLanguage = languageDtoToEntity(languageDto);
        var savedLanguage = languageRepository.save(newLanguage);
        return languageEntityToDto(savedLanguage);
    }

    public LanguageDto putLanguage(LanguageDto languageDto){
        return null;
    }

    public LanguageDto deleteLanguage(LanguageDto languageDto){
        return null;
    }
}

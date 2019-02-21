package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.LanguageDto;
import pl.dawidh.pierce.exception.NotFoundException;
import pl.dawidh.pierce.repository.LanguageRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static pl.dawidh.pierce.utils.ModelParseUtils.*;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    List<LanguageDto> getLanguages(){
        return languageCollectionEntityToListDto(languageRepository.findAll());
    }

    public List<LanguageDto> getLanguages(Long id, String languageCode){
        if(id != null){
            return Collections.singletonList(languageEntityToDto(languageRepository.findById(id).orElseThrow(() -> {
                throw new NotFoundException();
            })));
        } else if(languageCode != null){
            return languageCollectionEntityToListDto(languageRepository.findAllByCode(languageCode));
        } else {
            return languageCollectionEntityToListDto(languageRepository.findAll());
        }
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

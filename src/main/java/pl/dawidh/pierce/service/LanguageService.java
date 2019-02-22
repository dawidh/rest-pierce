package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.LanguageDto;
import pl.dawidh.pierce.exception.DuplicateException;
import pl.dawidh.pierce.exception.NotFoundException;
import pl.dawidh.pierce.repository.LanguageRepository;

import java.util.Collections;
import java.util.List;

import static pl.dawidh.pierce.utils.DateUtils.localDateTimeToTimestamp;
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
                var notFoundMassage = "Language with id='%d' not found";
                throw new NotFoundException(String.format(notFoundMassage, id));
            })));
        } else if(languageCode != null){
            return languageCollectionEntityToListDto(languageRepository.findAllByCode(languageCode));
        } else {
            return languageCollectionEntityToListDto(languageRepository.findAll());
        }
    }

    public LanguageDto saveLanguage(LanguageDto languageDto){
        isDuplicate(languageDto.getCode());
        var newLanguage = languageDtoToEntity(languageDto);
        var savedLanguage = languageRepository.save(newLanguage);
        return languageEntityToDto(savedLanguage);
    }

    public LanguageDto putLanguage(LanguageDto newData, Long id){
        var language = languageRepository.findById(id)
                .map(languageEntity -> {
                    languageEntity.setCode(isDuplicate(newData.getCode()));
                    languageEntity.setCreated(localDateTimeToTimestamp(newData.getCreated()));
                    return languageRepository.save(languageEntity);
                })
                .orElseGet(() -> languageRepository.save(languageDtoToEntity(newData)));
        return languageEntityToDto(language);
    }

    public LanguageDto deleteLanguage(Long languageId){
        var language = languageRepository.findById(languageId).orElseThrow(() -> {
            var notFoundMassage = "Language with id='%d' not found";
            throw new NotFoundException(String.format(notFoundMassage, languageId));
        });
        languageRepository.delete(language);
        return languageEntityToDto(language);
    }

    private String isDuplicate(String code){
        if(languageRepository.findByCodeEqualsIgnoreCase(code).isEmpty()){
            return code;
        } else {
            var duplicateErrorMassage = "Language '%s' already exists";
            throw new DuplicateException(String.format(duplicateErrorMassage, code));
        }
    }
}

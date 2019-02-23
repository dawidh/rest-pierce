package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.OptionTranslationDto;
import pl.dawidh.pierce.entity.LanguageEntity;
import pl.dawidh.pierce.entity.OptionEntity;
import pl.dawidh.pierce.exception.NotFoundException;
import pl.dawidh.pierce.repository.OptionTranslationRepository;

import java.util.Collections;
import java.util.List;

import static pl.dawidh.pierce.utils.ModelParseUtils.*;

@Service
public class OptionTranslationService {
    private final OptionTranslationRepository optionTranslationRepository;

    public OptionTranslationService(OptionTranslationRepository optionTranslationRepository) {
        this.optionTranslationRepository = optionTranslationRepository;
    }

    public List<OptionTranslationDto> getOptionTranslations(Long id, String translation, String attributeCode){
        if(id != null){
            return Collections.singletonList(optionTranslationEntityToDto(optionTranslationRepository.findById(id).orElseThrow(() -> {
                var notFoundMassage = "Option translation with id='%d' not found";
                throw new NotFoundException(String.format(notFoundMassage, id));
            })));
        } else if (translation != null && attributeCode != null){
            return optionTranslationCollectionEntityToListDto(optionTranslationRepository.findByTranslateAndOptionCode(translation, attributeCode));
        } else if (translation != null){
            return optionTranslationCollectionEntityToListDto(optionTranslationRepository.findByTranslateContainsIgnoreCase(translation));
        } else if (attributeCode != null){
            return optionTranslationCollectionEntityToListDto(optionTranslationRepository.findByOptionCode(attributeCode));
        } else {
            return optionTranslationCollectionEntityToListDto(optionTranslationRepository.findAll());
        }
    }

    public OptionTranslationDto saveOptionTranslation(OptionTranslationDto optionTranslationDto){
        var newOptionTranslation = optionTranslationDtoToEntity(optionTranslationDto);
        var savedOptionTranslation = optionTranslationRepository.save(newOptionTranslation);
        return optionTranslationEntityToDto(savedOptionTranslation);
    }

    public OptionTranslationDto putOptionTranslation(OptionTranslationDto newData, Long optionId){
        var translate = optionTranslationRepository.findById(optionId)
                .map(optionTranslationEntity -> {
                    optionTranslationEntity.setTranslate(newData.getTranslate());
                    optionTranslationEntity.setOption(new OptionEntity(newData.getOptionId()));
                    optionTranslationEntity.setLanguage(new LanguageEntity(newData.getLanguageId()));
                    return optionTranslationRepository.save(optionTranslationEntity);
                })
                .orElseGet(() -> optionTranslationRepository.save(optionTranslationDtoToEntity(newData)));
        return optionTranslationEntityToDto(translate);
    }

    public OptionTranslationDto deleteOptionTranslation(Long optionId){
        var optionTranslation = optionTranslationRepository.findById(optionId).orElseThrow(() -> {
            var notFoundMassage = "Option translate with id='%d' not found";
            throw new NotFoundException(String.format(notFoundMassage, optionId));
        });
        optionTranslationRepository.delete(optionTranslation);
        return optionTranslationEntityToDto(optionTranslation);
    }

    public void deleteOptionTranslationsByOptionId(Long optionId){
        optionTranslationRepository.deleteAllByOption_Id(optionId);
    }
}

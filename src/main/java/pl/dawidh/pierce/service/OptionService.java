package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.OptionDto;
import pl.dawidh.pierce.entity.AttributeEntity;
import pl.dawidh.pierce.exception.DuplicateException;
import pl.dawidh.pierce.exception.NotFoundException;
import pl.dawidh.pierce.repository.OptionRepository;

import java.util.Collections;
import java.util.List;

import static pl.dawidh.pierce.utils.ModelParseUtils.*;

@Service
public class OptionService {
    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    List<OptionDto> getOptions(){
        return optionCollectionEntityToListDto(optionRepository.findAll());
    }

    public List<OptionDto> getOptions(Long id, String code, String attributeCode){
        if(id != null){
            return Collections.singletonList(optionEntityToDto(optionRepository.findById(id).orElseThrow(() -> {
                var notFoundMassage = "Option with id='%d' not found";
                throw new NotFoundException(String.format(notFoundMassage, id));
            })));
        } else if (code != null && attributeCode != null){
            return optionCollectionEntityToListDto(optionRepository.findByCodeAndAttribute(code, attributeCode));
        } else if (code != null){
            return optionCollectionEntityToListDto(optionRepository.findByCodeEqualsIgnoreCase(code));
        } else if (attributeCode != null){
            return optionCollectionEntityToListDto(optionRepository.findByAttributeCode(attributeCode));
        } else {
            return optionCollectionEntityToListDto(optionRepository.findAll());
        }
    }

    public OptionDto saveOption(OptionDto optionDto){
        isDuplicate(optionDto.getCode(), optionDto.getAttributeId());
        var newOption = optionDtoToEntity(optionDto);
        var savedOption = optionRepository.save(newOption);
        return optionEntityToDto(savedOption);
    }

    public OptionDto putOption(OptionDto newData, Long id){
        isDuplicate(newData.getCode(), newData.getAttributeId());
        var option = optionRepository.findById(id)
                .map(optionEntity -> {
                    optionEntity.setCode(newData.getCode());
                    optionEntity.setSortOrder(newData.getSortOrder());
                    optionEntity.setAttribute(new AttributeEntity(newData.getAttributeId()));
                    return optionRepository.save(optionEntity);
                })
                .orElseGet(() -> optionRepository.save(optionDtoToEntity(newData)));
        return optionEntityToDto(option);
    }

    public OptionDto deleteOption(Long optionId){
        var option = optionRepository.findById(optionId).orElseThrow(() -> {
            var notFoundMassage = "Option with id='%d' not found";
            throw new NotFoundException(String.format(notFoundMassage, optionId));
        });
        optionRepository.delete(option);
        return optionEntityToDto(option);
    }

    private void isDuplicate(String code, Long attributeId){
        if(!optionRepository.findByCodeAndAttributeId(code, attributeId).isEmpty()){
            var duplicateErrorMassage = "Option '%s' with attribute '%d' id already exists";
            throw new DuplicateException(String.format(duplicateErrorMassage, code, attributeId));
        }
    }
}

package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.AttributeDto;
import pl.dawidh.pierce.exception.DuplicateException;
import pl.dawidh.pierce.exception.NotFoundException;
import pl.dawidh.pierce.repository.AttributeRepository;

import java.util.Collections;
import java.util.List;

import static pl.dawidh.pierce.utils.DateUtils.localDateTimeToTimestamp;
import static pl.dawidh.pierce.utils.ModelParseUtils.*;

@Service
public class AttributeService {
    private final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository){
        this.attributeRepository = attributeRepository;
    }

    List<AttributeDto> getAttributes(){
        return attributeCollectionEntityToListDto(attributeRepository.findAll());
    }

    public List<AttributeDto> getAttributes(Long id, String attributeCode){
        if(id != null){
            return Collections.singletonList(attributeEntityToDto(attributeRepository.findById(id).orElseThrow(() -> {
                var notFoundMassage = "Attribute with id='%d' not found";
                throw new NotFoundException(String.format(notFoundMassage, id));
            })));
        } else if(attributeCode != null){
            return attributeCollectionEntityToListDto(attributeRepository.findAllByCode(attributeCode));
        } else {
            return attributeCollectionEntityToListDto(attributeRepository.findAll());
        }
    }

    public AttributeDto saveAttribute(AttributeDto attributeDto){
        isDuplicate(attributeDto.getCode());
        var newAttribute = attributeDtoToEntity(attributeDto);
        var savedAttribute = attributeRepository.save(newAttribute);
        return attributeEntityToDto(savedAttribute);
    }

    public AttributeDto putAttribute(AttributeDto newData, Long id){
        var attribute = attributeRepository.findById(id)
                .map(attributeEntity -> {
                    attributeEntity.setCreated(localDateTimeToTimestamp(newData.getCreated()));
                    attributeEntity.setCode(isDuplicate(newData.getCode()));
                    return attributeRepository.save(attributeEntity);
                })
                .orElseGet(() -> attributeRepository.save(attributeDtoToEntity(newData)));
        return attributeEntityToDto(attribute);
    }

    public AttributeDto deleteAttribute(Long attributeId){
        var attribute = attributeRepository.findById(attributeId).orElseThrow(() -> {
            var notFoundMassage = "Attribute with id='%d' not found";
            throw new NotFoundException(String.format(notFoundMassage, attributeId));
        });
        attributeRepository.delete(attribute);
        return attributeEntityToDto(attribute);
    }

    private String isDuplicate(String code){
        if(attributeRepository.findByCodeEqualsIgnoreCase(code).isEmpty()){
            return code;
        } else {
            var duplicateErrorMassage = "Attribute '%s' already exists";
            throw new DuplicateException(String.format(duplicateErrorMassage, code));
        }
    }
}

package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.AttributeDto;
import pl.dawidh.pierce.repository.AttributeRepository;

import java.util.List;

import static pl.dawidh.pierce.utils.ModelParseUtils.*;

@Service
public class AttributeService {
    private final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository){
        this.attributeRepository = attributeRepository;
    }

    public List<AttributeDto> getAttributes(){
        return attributeCollectionEntityToListDto(attributeRepository.findAll());
    }

    public AttributeDto saveAttribute(AttributeDto attributeDto){
        var newAttribute = attributeDtoToEntity(attributeDto);
        var savedAttribute = attributeRepository.save(newAttribute);
        return attributeEntityToDto(savedAttribute);
    }

    public AttributeDto putAttribute(AttributeDto attributeDto){
        return null;
    }

    public AttributeDto deleteAttribute(AttributeDto attributeDto){
        return null;
    }
}

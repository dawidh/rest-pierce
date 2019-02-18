package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.AttributeDto;
import pl.dawidh.pierce.repository.AttributeRepository;

@Service
public class AttributeService {
    private final AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository){
        this.attributeRepository = attributeRepository;
    }

    public AttributeDto saveAttribute(AttributeDto attributeDto){
        return null;
    }

    public AttributeDto putAttribute(AttributeDto attributeDto){
        return null;
    }

    public AttributeDto deleteAttribute(AttributeDto attributeDto){
        return null;
    }
}

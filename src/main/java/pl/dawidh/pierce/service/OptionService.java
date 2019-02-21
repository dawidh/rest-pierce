package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.OptionDto;
import pl.dawidh.pierce.repository.OptionRepository;

import java.util.List;

import static pl.dawidh.pierce.utils.ModelParseUtils.*;

@Service
public class OptionService {
    private final OptionRepository optionRepository;

    public List<OptionDto> getOptions(){
        return optionCollectionEntityToListDto(optionRepository.findAll());
    }

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public OptionDto saveOption(OptionDto optionDto){
        var newOption = optionDtoToEntity(optionDto);
        var savedOption = optionRepository.save(newOption);
        return optionEntityToDto(savedOption);
    }

    public OptionDto putOption(OptionDto optionDto){
        return null;
    }

    public OptionDto deleteOption(OptionDto optionDto){
        return null;
    }
}

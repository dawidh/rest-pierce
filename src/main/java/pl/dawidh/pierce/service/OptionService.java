package pl.dawidh.pierce.service;

import org.springframework.stereotype.Service;
import pl.dawidh.pierce.controller.dto.OptionDto;
import pl.dawidh.pierce.repository.OptionRepository;

@Service
public class OptionService {
    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public OptionDto saveOption(OptionDto optionDto){
        return null;
    }

    public OptionDto putOption(OptionDto optionDto){
        return null;
    }

    public OptionDto deleteOption(OptionDto optionDto){
        return null;
    }
}

package pl.dawidh.pierce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dawidh.pierce.service.OptionService;
import pl.dawidh.pierce.service.OptionTranslationService;

@RestController
@RequestMapping(path = "/options",
        consumes = "application/json",
        produces = "application/json")
public class OptionController {
    private final OptionService optionService;
    private final OptionTranslationService attributeService;

    public OptionController(OptionService optionService, OptionTranslationService attributeService) {
        this.optionService = optionService;
        this.attributeService = attributeService;
    }
}

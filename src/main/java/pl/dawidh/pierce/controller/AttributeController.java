package pl.dawidh.pierce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dawidh.pierce.service.AttributeService;
import pl.dawidh.pierce.service.AttributeTranslationService;

@RestController
@RequestMapping(path = "/attributes",
        consumes = "application/json",
        produces = "application/json")
public class AttributeController {
    private final AttributeService attributeService;
    private final AttributeTranslationService attributeTranslationService;

    private AttributeController(AttributeService attributeService, AttributeTranslationService attributeTranslationService) {
        this.attributeService = attributeService;
        this.attributeTranslationService = attributeTranslationService;
    }
}

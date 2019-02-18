package pl.dawidh.pierce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dawidh.pierce.service.LanguageService;

@RestController
@RequestMapping(path = "/languages",
        consumes = "application/json",
        produces = "application/json")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }
}

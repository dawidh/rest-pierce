package pl.dawidh.pierce.controller;

import org.springframework.web.bind.annotation.*;
import pl.dawidh.pierce.controller.dto.LanguageDto;
import pl.dawidh.pierce.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping(path = "/languages",
        consumes = "application/json",
        produces = "application/json")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping(path = {"", "/{id}"})
    private @ResponseBody
    List<LanguageDto> getLanguages(@PathVariable(required = false) Long id,
                                   @RequestParam(required = false) String code){
        return languageService.getLanguages(id, code);
    }
}

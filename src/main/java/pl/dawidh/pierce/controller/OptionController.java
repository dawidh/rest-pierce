package pl.dawidh.pierce.controller;

import org.springframework.web.bind.annotation.*;
import pl.dawidh.pierce.controller.dto.OptionDto;
import pl.dawidh.pierce.controller.dto.OptionTranslationDto;
import pl.dawidh.pierce.service.OptionService;
import pl.dawidh.pierce.service.OptionTranslationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/options",
        consumes = "application/json",
        produces = "application/json")
public class OptionController {
    private final OptionService optionService;
    private final OptionTranslationService optionTranslationService;

    public OptionController(OptionService optionService, OptionTranslationService optionTranslationService) {
        this.optionService = optionService;
        this.optionTranslationService = optionTranslationService;
    }

    @GetMapping(path = {"", "/{id}"})
    private @ResponseBody
    List<OptionDto> getOptions(@PathVariable(required = false) Long id,
                                @RequestParam(required = false) String code,
                                @RequestParam(required = false) String attributeCode){
        return optionService.getOptions(id, code, attributeCode);
    }

    @PostMapping(path = {""})
    private OptionDto addOption(@Valid @RequestBody OptionDto newOption) {
        return optionService.saveOption(newOption);
    }

    @PutMapping(path = {"", "/{id}"})
    private OptionDto putOption(@Valid @RequestBody OptionDto newData,
                                      @PathVariable(required = false) Long id) {
        return optionService.putOption(newData, id);
    }

    @DeleteMapping("/{id}")
    private OptionDto deleteOption(@PathVariable Long id) {
        optionTranslationService.deleteOptionTranslationsByOptionId(id);
        return optionService.deleteOption(id);
    }

    @GetMapping(path = {"/translations", "/translations/{id}"})
    private @ResponseBody
    List<OptionTranslationDto> getTranslations(@PathVariable(required = false) Long id,
                                               @RequestParam(required = false) String translation,
                                               @RequestParam(required = false) String attributeCode){
        return optionTranslationService.getOptionTranslations(id, translation, attributeCode);
    }

    @PostMapping("/translations")
    private OptionTranslationDto addTranslation(@Valid @RequestBody OptionTranslationDto newAttribute) {
        return optionTranslationService.saveOptionTranslation(newAttribute);
    }

    @PutMapping(path = {"/translations", "/translations/{id}"})
    private OptionTranslationDto putTranslation(@Valid @RequestBody OptionTranslationDto newData,
                                                   @PathVariable(required = false) Long id) {
        return optionTranslationService.putOptionTranslation(newData, id);
    }

    @DeleteMapping("/translations/{id}")
    private OptionTranslationDto deleteTranslation(@PathVariable Long id) {
        return optionTranslationService.deleteOptionTranslation(id);
    }

}

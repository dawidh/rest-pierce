package pl.dawidh.pierce.controller;

import org.springframework.web.bind.annotation.*;
import pl.dawidh.pierce.controller.dto.AttributeDto;
import pl.dawidh.pierce.controller.dto.AttributeTranslationDto;
import pl.dawidh.pierce.service.AttributeService;
import pl.dawidh.pierce.service.AttributeTranslationService;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping(path = {"", "/{id}"})
    private @ResponseBody
    List<AttributeDto> getAttributes(@PathVariable(required = false) Long id,
                                     @RequestParam(required = false) String code){
        return attributeService.getAttributes(id, code);
    }

    @PostMapping(path = {""})
    private AttributeDto addAttribute(@Valid @RequestBody AttributeDto newAttribute) {
        return attributeService.saveAttribute(newAttribute);
    }

    @PutMapping(path = {"", "/{id}"})
    private AttributeDto putAttribute(@Valid @RequestBody AttributeDto newData,
                                      @PathVariable(required = false) Long id) {
        return attributeService.putAttribute(newData, id);
    }

    @DeleteMapping("/{id}")
    private AttributeDto deleteAttribute(@PathVariable Long id) {
        attributeTranslationService.deleteAttributeTranslationsByAttributeId(id);
        return attributeService.deleteAttribute(id);
    }

    @GetMapping(path = {"/translations", "/translations/{id}"})
    private @ResponseBody
    List<AttributeTranslationDto> getTranslations(@PathVariable(required = false) Long id,
                                                  @RequestParam(required = false) String translation,
                                                  @RequestParam(required = false) String attributeCode){
        return attributeTranslationService.getAttributeTranslations(id, translation, attributeCode);
    }

    @PostMapping("/translations")
    private AttributeTranslationDto addTranslation(@Valid @RequestBody AttributeTranslationDto newAttribute) {
        return attributeTranslationService.saveAttributeTranslation(newAttribute);
    }

    @PutMapping(path = {"/translations", "/translations/{id}"})
    private AttributeTranslationDto putTranslation(@Valid @RequestBody AttributeTranslationDto newData,
                                      @PathVariable(required = false) Long id) {
        return attributeTranslationService.putAttributeTranslation(newData, id);
    }

    @DeleteMapping("/translations/{id}")
    private AttributeTranslationDto deleteTranslation(@PathVariable Long id) {
        return attributeTranslationService.deleteAttributeTranslation(id);
    }
}

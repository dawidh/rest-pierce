package pl.dawidh.pierce.controller;

import org.springframework.web.bind.annotation.*;
import pl.dawidh.pierce.controller.dto.AttributeDto;
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

    @PostMapping
    private AttributeDto addAttributes(@Valid @RequestBody AttributeDto newAttribute) {
        return attributeService.saveAttribute(newAttribute);
    }

    @PutMapping(path = {"", "/{id}"})
    private AttributeDto putAttribute(@Valid @RequestBody AttributeDto newData,
                                      @PathVariable(required = false) Long id) {
        return attributeService.putAttribute(newData, id);
    }

    @DeleteMapping("/{id}")
    private AttributeDto deleteAttribute(@PathVariable Long id) {
        return attributeService.deleteAttribute(id);
    }
}

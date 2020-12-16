package edu.netcracker.menugenerator.controllers;


import edu.netcracker.menugenerator.dto.PreferenceDto;
import edu.netcracker.menugenerator.dto.response.MessageResponse;
import edu.netcracker.menugenerator.entity.Preference;
import edu.netcracker.menugenerator.services.PreferenceService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"${menuGenerator.cors.allowedOrigin}"}, maxAge = 3600, exposedHeaders = "X-Has-Next-Page")
@RestController
@RequestMapping("/preference")
@Slf4j
public class PreferencesController {

    private final PreferenceService preferenceService;
    private final Mapper mapper;

    @Autowired
    public PreferencesController(PreferenceService preferenceService, Mapper mapper){
        this.preferenceService = preferenceService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id, Pageable pageable) {
        List<PreferenceDto> pref = preferenceService.getList(id, pageable).stream().map(
                item -> mapper.map(item, PreferenceDto.class)
        ).collect(Collectors.toList());
        return ResponseEntity.ok(pref);
    }

    @PostMapping(value = "/add/{ban}")
    public ResponseEntity<?> add(@RequestParam long id, @RequestParam long userId, @PathVariable(name = "ban") boolean ban) {
        Preference preference = preferenceService.add(id, userId, ban);
        if(preference == null) return ResponseEntity.ok(new MessageResponse("Уже добавлено один из списков.", true));
        return ResponseEntity.ok(mapper.map(preference, PreferenceDto.class));
    }

    @PostMapping(value = "/remove")
    public ResponseEntity<?> remove(@RequestParam long id, @RequestParam long userId) {
        return ResponseEntity.ok(preferenceService.remove(id, userId));
    }
}

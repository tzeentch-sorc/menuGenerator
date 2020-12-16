package edu.netcracker.menugenerator.controllers;

import edu.netcracker.menugenerator.dto.ProfileDto;
import edu.netcracker.menugenerator.services.ProfileService;
import edu.netcracker.menugenerator.util.exceptions.ProfileNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"${menuGenerator.cors.allowedOrigin}"}, maxAge = 3600)
@RestController
@RequestMapping("/profile")
@Slf4j
public class ProfileController {

    private final ProfileService profileService;
    Mapper mapper;

    @Autowired
    public ProfileController(ProfileService profileService, Mapper mapper){
        this.profileService = profileService;
        this.mapper = mapper;
    }

    @PostMapping(value = "/set/{id}")
    public ResponseEntity<?> setProfileById(@PathVariable(name = "id") long id, @RequestParam String profile) {
        try {
            return ResponseEntity.ok(mapper.map(profileService.updateByUserId(id, profile), ProfileDto.class));
        } catch (ProfileNotValidException e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping(value = "/get/{id}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable(name = "id") long id) {
            return ResponseEntity.ok(mapper.map(profileService.getByUserId(id), ProfileDto.class));
    }
}

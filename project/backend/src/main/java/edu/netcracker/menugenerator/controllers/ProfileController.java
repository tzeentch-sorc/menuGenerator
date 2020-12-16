package edu.netcracker.menugenerator.controllers;

import edu.netcracker.menugenerator.dto.ProfileDto;
import edu.netcracker.menugenerator.services.ProfileService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"${menuGenerator.cors.allowedOrigin}"}, maxAge = 3600)
@RestController
@RequestMapping("/profile")
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
            return ResponseEntity.ok(mapper.map(profileService.updateByUserId(id, profile), ProfileDto.class));
    }

    @PostMapping(value = "/get/{id}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable(name = "id") long id) {
            return ResponseEntity.ok(mapper.map(profileService.getByUserId(id), ProfileDto.class));
    }
}

package edu.netcracker.menugenerator.controllers;

import edu.netcracker.menugenerator.dto.MealDto;
import edu.netcracker.menugenerator.services.MealService;
import javassist.NotFoundException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/meals")
public class MealController {
    private final MealService mealService;
    private final Mapper mapper;

    @Autowired
    public MealController(MealService mealService, Mapper mapper) {
        this.mealService = mealService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getMealById(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(mapper.map(mealService.getById(id), MealDto.class));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<?>> getAllMeals() {
        List<MealDto> meals = mealService.getAllMeals().stream().map(
                e -> mapper.map(e, MealDto.class)
        ).collect(Collectors.toList());
        return ResponseEntity.ok().body(meals);
    }

    @GetMapping("/{title}")
    public ResponseEntity<?> getMealById(@PathVariable(name = "title") String title) {
        try {
            return ResponseEntity.ok(mapper.map(mealService.getByName(title), MealDto.class));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
}

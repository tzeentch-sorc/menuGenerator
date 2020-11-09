package edu.netcracker.recipedb.controllers;

import edu.netcracker.recipedb.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.netcracker.recipedb.models.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081/")
@RestController
public class MealController {
    private MealRepository mealRepository;

    @Autowired
    public MealController() {
    }

    @GetMapping(value = "/meal/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable(name = "id") Long id) {
        Optional<Meal> meal =  mealRepository.findById(id);
        if (meal.isPresent()) {
            return new ResponseEntity<>(meal.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/meals")
    public ResponseEntity<List<Meal>> getAllTutorials(@RequestParam(required = false) String name) {
        try {
            List<Meal> meals = new ArrayList<Meal>();

            if (name == null)
                mealRepository.findAll().forEach(meals::add);
            else
                mealRepository.findByNameContaining(name).forEach(meals::add);

            if (meals.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(meals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package edu.netcracker.menugenerator.controllers;

import edu.netcracker.menugenerator.dto.MealDto;
import edu.netcracker.menugenerator.services.MealService;
import edu.netcracker.menugenerator.util.PaginationUtil;
import javassist.NotFoundException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<?> getAllMeals(@RequestParam(required = false) String filters, Pageable pageable) {
        Slice<MealDto> meals = mealService.getAllMeals(filters, pageable).map(
                e -> mapper.map(e, MealDto.class)
        );
        return ResponseEntity.ok().headers(PaginationUtil.generateSliceHttpHeaders(meals)).body(meals.getContent());
    }
}

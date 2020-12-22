package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.entity.Meal;
import edu.netcracker.menugenerator.util.exceptions.MealNotFoundException;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MealService {
    Slice<Meal> getAllMeals(String filters, Pageable pageable);

    Meal getById(long id) throws MealNotFoundException;

    Meal getRandom() throws MealNotFoundException;
}

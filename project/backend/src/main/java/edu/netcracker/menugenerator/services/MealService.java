package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.entity.Meal;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MealService {
    Slice<Meal> getAllMeals(String filters, Pageable pageable);

    Meal getById(long id) throws NotFoundException;

    Meal getRandom() throws NotFoundException;
}

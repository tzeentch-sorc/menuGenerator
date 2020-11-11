package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.entity.Meal;
import javassist.NotFoundException;

import java.util.List;

public interface MealService {
    List<Meal> getAllMeals();

    Meal getById(long id) throws NotFoundException;

    Meal getByName(String name) throws NotFoundException;
}

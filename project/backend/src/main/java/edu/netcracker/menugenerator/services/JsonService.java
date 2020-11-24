package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.entity.MealFilters;

public interface JsonService {
    String toJson(Object o);

    public MealFilters parseMealFilters(String src);
}

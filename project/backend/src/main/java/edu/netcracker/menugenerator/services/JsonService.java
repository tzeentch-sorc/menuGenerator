package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.util.MealFilters;

public interface JsonService {
    String toJson(Object o);

    public MealFilters parseMealFilters(String src);
}

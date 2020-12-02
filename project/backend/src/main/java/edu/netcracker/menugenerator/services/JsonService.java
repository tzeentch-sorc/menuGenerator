package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.dto.ProfileDto;
import edu.netcracker.menugenerator.dto.MealFilters;

public interface JsonService {
    String toJson(Object o);

    public MealFilters parseMealFilters(String src);

    public ProfileDto parseProfileDto(String src);
}

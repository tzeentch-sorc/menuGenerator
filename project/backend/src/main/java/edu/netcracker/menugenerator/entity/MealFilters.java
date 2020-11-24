package edu.netcracker.menugenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealFilters {
    String text;
    MealType mealType;
    boolean searchInRecipe;
}

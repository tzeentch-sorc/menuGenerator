package edu.netcracker.menugenerator.dto;

import edu.netcracker.menugenerator.util.MealType;
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

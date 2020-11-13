package edu.netcracker.menugenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDto {
    private long id;
    private String name;
    private String recipe;
    private String mealtype;
    private int calories;
    private int portions;
    private double fats;
    private double carbohydrates;
    private int weight;
    private double proteins;
    private String picture;
}

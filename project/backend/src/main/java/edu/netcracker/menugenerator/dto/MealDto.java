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
    private double fats;
    private double carbohydrates;
    private double proteins;
    private  String picture;
}

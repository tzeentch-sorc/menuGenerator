package edu.netcracker.menugenerator.dto;

import edu.netcracker.menugenerator.util.MealType;
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
    private MealType type;
}

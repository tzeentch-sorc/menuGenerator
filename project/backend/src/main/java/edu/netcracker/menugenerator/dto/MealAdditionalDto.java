package edu.netcracker.menugenerator.dto;

import edu.netcracker.menugenerator.entity.MealProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealAdditionalDto {
    private String recipe;
    private String type;
    private int calories;
    private int portions;
    private int weight;
    private List<MealProduct> mealProducts;
}

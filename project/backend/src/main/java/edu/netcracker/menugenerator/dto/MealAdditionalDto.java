package edu.netcracker.menugenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealAdditionalDto {
    private String recipe;
    private String type;
    private int calories;
    private int portions;
    private int weight;
}

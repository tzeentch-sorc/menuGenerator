package edu.netcracker.menugenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private long id;
    private String name;
    private String description;
    private ArrayList<MealDto> mealsInMenu;
}

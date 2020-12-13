package edu.netcracker.menugenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

import java.util.ArrayList;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDtoAdditional {
    private ArrayList<MealDto> mealsInMenu;
}

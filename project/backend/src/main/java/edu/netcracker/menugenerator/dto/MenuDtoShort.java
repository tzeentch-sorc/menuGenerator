package edu.netcracker.menugenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDtoShort {
    private long id;
    private String name;
    private String description;
    private int mealsLength;
}

package edu.netcracker.menugenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private long id;
    private int height;
    private int weight;
    private int age;
    private boolean male;
    private double activity;
    private double dailyFats;
    private double dailyCarbohydrates;
    private double dailyProteins;
    private double dailyCalories;
}

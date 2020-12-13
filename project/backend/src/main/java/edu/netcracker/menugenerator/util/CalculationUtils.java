package edu.netcracker.menugenerator.util;

import edu.netcracker.menugenerator.dto.ProfileDto;
import edu.netcracker.menugenerator.dto.PfccDto;
import edu.netcracker.menugenerator.entity.Meal;

import java.util.Random;
import java.util.Set;

public class CalculationUtils {
    public static final double upCoef = 1.2;
    public static final double downCoef = 0.6;
    public static final double gap = 100;
    public static final Random random = new Random();


    public static PfccDto calcCalories(ProfileDto stats){
        PfccDto calc = new PfccDto();
        double calories = 10 * stats.getWeight() + 6.25 * stats.getWeight() - 5 * stats.getAge();
        if(stats.isMale()){
            calories += 5;
        }
        else {
            calories -= 161;
        }
        calories *= stats.getActivity();
        calc.setCalories(calories);
        calc.setFats(calories * 0.4 / 9);
        calc.setCarbo(calories * 0.3 / 4);
        calc.setProteins(calories * 0.3 / 4);
        return calc;
    }

    public static int calcTotalCalories(Set<Meal> mealsInMenu){
        int sum = 0;
        Meal[] meals = mealsInMenu.toArray(Meal[]::new);
        for(int i = 0; i < mealsInMenu.size(); i++)
            sum += meals[i].getCalories();
        return sum;
    }

    public static double calcWindowedValue(double value){
        double randomizedWindow = gap + gap * random.nextDouble();
        return value + random.nextInt(2)* (-1) * randomizedWindow;
    }
}

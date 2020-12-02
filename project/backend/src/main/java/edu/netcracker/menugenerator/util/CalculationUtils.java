package edu.netcracker.menugenerator.util;

import edu.netcracker.menugenerator.dto.ProfileDto;
import edu.netcracker.menugenerator.dto.PfccDto;

public class CalculationUtils {
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
}

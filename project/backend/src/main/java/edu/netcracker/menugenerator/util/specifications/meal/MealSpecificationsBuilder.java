package edu.netcracker.menugenerator.util.specifications.meal;

import edu.netcracker.menugenerator.entity.Meal;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MealSpecificationsBuilder {
    private final List<SearchCriteria> params;

    public MealSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public MealSpecificationsBuilder with(String key, String operation, Object value, boolean orPredicate) {
        params.add(new SearchCriteria(key, operation, value, orPredicate));
        return this;
    }

    public Specification<Meal> build() {
        if (params.size() == 0) {
            return null;
        }
        Specification<Meal> result = new MealSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new MealSpecification(params.get(i)))
                    : Specification.where(result).and(new MealSpecification(params.get(i)));
        }

        return result;
    }

}

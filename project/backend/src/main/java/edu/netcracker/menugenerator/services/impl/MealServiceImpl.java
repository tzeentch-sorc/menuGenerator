package edu.netcracker.menugenerator.services.impl;

import edu.netcracker.menugenerator.entity.Meal;
import edu.netcracker.menugenerator.dto.MealFilters;
import edu.netcracker.menugenerator.util.MealType;
import edu.netcracker.menugenerator.repository.MealRepository;
import edu.netcracker.menugenerator.services.JsonService;
import edu.netcracker.menugenerator.services.MealService;
import edu.netcracker.menugenerator.util.exceptions.MealNotFoundException;
import edu.netcracker.menugenerator.util.specifications.meal.MealSpecification;
import edu.netcracker.menugenerator.util.specifications.meal.MealSpecificationsBuilder;
import edu.netcracker.menugenerator.util.specifications.meal.SearchCriteria;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final JsonService jsonService;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository, JsonService jsonService){
        this.mealRepository = mealRepository;
        this.jsonService = jsonService;
    }

    @Override
    public Slice<Meal> getAllMeals(String filters, Pageable pageable) {
        MealFilters mealFilters;
        MealSpecificationsBuilder builder = new MealSpecificationsBuilder();

        if(filters == null) mealFilters = new MealFilters("", MealType.TYPE_ALL, false);
        else mealFilters = jsonService.parseMealFilters(filters);


        Specification<Meal> specification;
        if(mealFilters.getText().length() > 0) {
            builder.with("name", ":", mealFilters.getText(), false);
            if (!mealFilters.isSearchInRecipe()) {
                builder.with("recipe", ":", mealFilters.getText(), true);
            }
            specification = builder.build();
            if (mealFilters.getMealType() != MealType.TYPE_ALL)
                specification = specification.and(new MealSpecification(new SearchCriteria("type", ":", mealFilters.getMealType(), false)));
        } else specification = builder.build();

        return mealRepository.findAll(specification, pageable);
    }
    @Override
    public Meal getById(long id) throws MealNotFoundException {
        return mealRepository.findById(id).orElseThrow(
                () -> new MealNotFoundException("Meal by id: " + id + " not found.")
        );
    }

    @Override
    public Meal getRandom() throws MealNotFoundException {
        long max = mealRepository.count();
        Random r = new Random();
        long id = r.nextInt((int)max-1)+1;
        return mealRepository.findById(id).orElseThrow(
                () -> new MealNotFoundException("Meal by id: " + id + " not found.")
        );
    }
}

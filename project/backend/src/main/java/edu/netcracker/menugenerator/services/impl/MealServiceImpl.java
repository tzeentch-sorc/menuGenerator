package edu.netcracker.menugenerator.services.impl;

import edu.netcracker.menugenerator.entity.Meal;
import edu.netcracker.menugenerator.util.MealFilters;
import edu.netcracker.menugenerator.util.MealType;
import edu.netcracker.menugenerator.repository.MealRepository;
import edu.netcracker.menugenerator.services.JsonService;
import edu.netcracker.menugenerator.services.MealService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        if(filters == null) mealFilters = new MealFilters("", MealType.TYPE_ALL, false);
        else mealFilters = jsonService.parseMealFilters(filters);
        if(mealFilters.getText().length() > 0){
            if(mealFilters.getMealType() == MealType.TYPE_ALL){
                if(mealFilters.isSearchInRecipe()){
                    return mealRepository.findAllByRecipeIgnoreCaseContaining(mealFilters.getText(), pageable);
                } else {
                    return mealRepository.findAllByNameIgnoreCaseContaining(mealFilters.getText(), pageable);
                }
            } else {
                if(mealFilters.isSearchInRecipe()){
                    return mealRepository.findAllByRecipeIgnoreCaseContainingAndType(mealFilters.getText(), mealFilters.getMealType(), pageable);
                } else {
                    return mealRepository.findAllByNameContainingIgnoreCaseAndType(mealFilters.getText(), mealFilters.getMealType(), pageable);
                }
            }
        } else {
            if(mealFilters.getMealType() != MealType.TYPE_ALL) {
                return mealRepository.findAllByType(mealFilters.getMealType(), pageable);
            } else return mealRepository.findAll(pageable);
        }
    }
    @Override
    public Meal getById(long id) throws NotFoundException{
        return mealRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Meal by id: " + id + "not found.")
        );
    }
}

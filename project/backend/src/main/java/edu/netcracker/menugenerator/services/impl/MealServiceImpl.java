package edu.netcracker.menugenerator.services.impl;

import edu.netcracker.menugenerator.entity.Meal;
import edu.netcracker.menugenerator.repository.MealRepository;
import edu.netcracker.menugenerator.services.MealService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository){
        this.mealRepository = mealRepository;
    }


    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @Override
    public Meal getById(long id) throws NotFoundException{
        return mealRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Meal by id: " + id + "not found.")
        );
    }

    @Override
    public Meal getByName(String name) throws NotFoundException {
        return mealRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Meal by name: " + name + "; not found.")
        );
    }
}
package edu.netcracker.menugenerator.services.impl;

import edu.netcracker.menugenerator.entity.Meal;
import edu.netcracker.menugenerator.entity.Menu;
import edu.netcracker.menugenerator.repository.MealRepository;
import edu.netcracker.menugenerator.repository.MenuRepository;
import edu.netcracker.menugenerator.repository.ProfileRepository;
import edu.netcracker.menugenerator.repository.UserRepository;
import edu.netcracker.menugenerator.services.MenuService;
import edu.netcracker.menugenerator.util.CalculationUtils;
import edu.netcracker.menugenerator.util.MealType;
import edu.netcracker.menugenerator.util.exceptions.MenuNotFoundException;
import edu.netcracker.menugenerator.util.specifications.meal.MealSpecificationsBuilder;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@Slf4j
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final ProfileRepository profileRepository;
    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository, ProfileRepository profileRepository,
                           MealRepository mealRepository, UserRepository userRepository){
        this.menuRepository = menuRepository;
        this.profileRepository = profileRepository;
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Menu getMenuById(long id) throws MenuNotFoundException{
            return menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException("Menu by id: " + id + " not found."));
    }

    @Override
    public Slice<Menu> getAll(long id, String name, Pageable pageable) {
        if(name == null){
            return menuRepository.findAllByCurrentIsFalseAndUserId(id, pageable);
        }
        else
            return menuRepository.findAllByCurrentIsFalseAndUserIdAndNameContaining(id, name, pageable);
    }

    @Override
    public Menu setCurrent(long id, long userId) throws MenuNotFoundException {
        Menu current = getCurrent(userId);
        if(!isSaved(current))
        {
            menuRepository.delete(current);
            current = null;
        } else {
            current.setCurrent(false);
            current = menuRepository.save(current);
        }

        Menu newMenu = getMenuById(id);
        if(isSaved(newMenu)) {
            newMenu.setCurrent(true);
            menuRepository.save(newMenu);
        }
        return current;
    }

    private boolean isSaved(Menu menu){
        return menu.getName() != null && menu.getDescription() != null;
    }

    @Override
    public Menu getCurrent(long id) {
        Menu menu = menuRepository.findMenuByCurrentIsTrueAndUserId(id).orElse(null);
        if(menu == null)
            menu = generateMenu(id);
        return menu;
    }

    @Override
    public Menu updateMenu(long id, String name, String description) throws  MenuNotFoundException{
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException("Menu(" + id + ") not found"));
        menu.setName(name);
        menu.setDescription(description);
        return menuRepository.save(menu);
    }

    @Override
    public Menu generateMenu(long userId) {
        double caloriesInit = profileRepository.findFirstByUserId(userId).getDailyCalories();
        Menu menu = menuRepository.findMenuByCurrentIsTrueAndUserId(userId).orElse(new Menu());
        if(menu.getName() != null && menu.getDescription() != null){
            menu.setCurrent(false);
            menuRepository.save(menu);
            menu = new Menu();
        }

        Set<Meal> mealsInMenu = new HashSet<>();
        MealSpecificationsBuilder builder;
        Specification<Meal> specification;
        List<Meal>found;
        Meal chosenOne;
        double calories = CalculationUtils.calcWindowedValue(caloriesInit);
        double searchCalories = calories / 3;
        for (int i = 0; i < MealType.values().length - 1; i++){
            builder = new MealSpecificationsBuilder();
            builder.with("calories", "<", (int)(searchCalories * CalculationUtils.upCoef), false);
            builder.with("calories", ">", (int)(searchCalories * CalculationUtils.downCoef), false);
            builder.with("type", ":", MealType.values()[i], false);
            specification = builder.build();
            found = mealRepository.findAll(specification);
            if(found.size() > 0){
                chosenOne = found.get(CalculationUtils.random.nextInt(found.size()));
            } else {
                break;//TODO fix
            }

            mealsInMenu.add(chosenOne);
            calories -= chosenOne.getCalories();
            if(calories < 0) break;
        }
        menu.setMealsInMenu(mealsInMenu);
        menu.setCurrent(true);
        menu.setUser(userRepository.findById(userId).get());
        menuRepository.save(menu);
        return menu;
    }

    @Override
    public void deleteById(long id){
        menuRepository.deleteById(id);
    }
}

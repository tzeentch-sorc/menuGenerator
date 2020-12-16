package edu.netcracker.menugenerator.services.impl;

import edu.netcracker.menugenerator.dto.response.MessageResponse;
import edu.netcracker.menugenerator.entity.Meal;
import edu.netcracker.menugenerator.entity.Preference;
import edu.netcracker.menugenerator.entity.User;
import edu.netcracker.menugenerator.repository.MealRepository;
import edu.netcracker.menugenerator.repository.PreferenceRepository;
import edu.netcracker.menugenerator.repository.UserRepository;
import edu.netcracker.menugenerator.services.PreferenceService;
import edu.netcracker.menugenerator.util.exceptions.AlreadyExistException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PreferenceServiceImpl implements PreferenceService {
    private PreferenceRepository preferenceRepository;
    private UserRepository userRepository;
    private MealRepository mealRepository;

    @Autowired
    public PreferenceServiceImpl(PreferenceRepository preferenceRepository, UserRepository userRepository, MealRepository mealRepository){
        this.preferenceRepository = preferenceRepository;
        this.userRepository = userRepository;
        this.mealRepository = mealRepository;
    }


    @Override
    public Slice<Preference> getList(long userId, Pageable pageable){
        return preferenceRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Preference add(long mealId, long userId, boolean ban) throws AlreadyExistException {
        Preference preference = preferenceRepository.findByMealIdAndUserId(mealId, userId);
        if(preference != null) throw new AlreadyExistException("Preference of user(" + userId + ") of meal(" + mealId +") already exist");
        preference = new Preference();
        User user = userRepository.findById(userId).get();
        Meal meal = mealRepository.findById(mealId).get();
        preference.setBanned(ban);
        preference.setMeal(meal);
        preference.setUser(user);
        return preferenceRepository.save(preference);
    }

    @Override
    public MessageResponse remove(long mealId, long userId){
        Preference preference = preferenceRepository.findByMealIdAndUserId(mealId, userId);
        if(preference == null) return new MessageResponse("Выбранного блюда нет ни в одном из списков.", true);
        preferenceRepository.removeByMealIdAndUserId(mealId, userId);
        return new MessageResponse("Удалено.", true);
    }
}

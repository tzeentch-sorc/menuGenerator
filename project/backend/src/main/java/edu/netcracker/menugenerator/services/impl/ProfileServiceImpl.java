package edu.netcracker.menugenerator.services.impl;

import edu.netcracker.menugenerator.dto.PfccDto;
import edu.netcracker.menugenerator.dto.ProfileDto;
import edu.netcracker.menugenerator.entity.Profile;
import edu.netcracker.menugenerator.entity.User;
import edu.netcracker.menugenerator.repository.ProfileRepository;
import edu.netcracker.menugenerator.repository.UserRepository;
import edu.netcracker.menugenerator.services.JsonService;
import edu.netcracker.menugenerator.services.ProfileService;
import edu.netcracker.menugenerator.util.CalculationUtils;
import edu.netcracker.menugenerator.util.exceptions.ProfileNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
    ProfileRepository profileRepository;
    UserRepository userRepository;
    JsonService jsonService;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, UserRepository userRepository, JsonService jsonService){
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.jsonService = jsonService;
    }

    @Override
    public Profile getByUserId(long userId) {
        return profileRepository.findFirstByUserId(userId);
    }

    @Override
    public Profile updateByUserId(long userId, String profileStr) throws ProfileNotValidException{
        ProfileDto profileDto = jsonService.parseProfileDto(profileStr);
        User user = userRepository.findById(userId).get();
        PfccDto stats = CalculationUtils.calcCalories(profileDto);
        if(profileDto.getHeight() <= 0 || profileDto.getWeight() <= 0 || profileDto.getAge() <= 0)
            throw new ProfileNotValidException("Some stats are below or equal to zero.");
        Profile profile = new Profile(
                profileDto.getId(), profileDto.getHeight(), profileDto.getWeight(),
                profileDto.getAge(), profileDto.isMale(), profileDto.getActivity(),
                stats.getFats(), stats.getCarbo(), stats.getProteins(), stats.getCalories(), user);
        return profileRepository.save(profile);
    }
}

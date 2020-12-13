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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Profile updateByUserId(long userId, String profileStr) {
        //TODO add error handling
        ProfileDto profileDto = jsonService.parseProfileDto(profileStr);
        Optional<User> userOptional = userRepository.findById(userId);
        PfccDto stats = CalculationUtils.calcCalories(profileDto);
        User user;
        user = userOptional.orElse(null);
        Profile profile = new Profile(
                profileDto.getId(), profileDto.getHeight(), profileDto.getWeight(),
                profileDto.getAge(), profileDto.isMale(), profileDto.getActivity(),
                stats.getFats(), stats.getCarbo(), stats.getProteins(), stats.getCalories(), user);
        return profileRepository.save(profile);
    }
}

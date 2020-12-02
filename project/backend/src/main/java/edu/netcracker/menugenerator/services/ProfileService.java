package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.entity.Profile;

public interface ProfileService {
    Profile getByUserId(long userId);

    Profile UpdateByUserId(long userId, String profileStr);
}

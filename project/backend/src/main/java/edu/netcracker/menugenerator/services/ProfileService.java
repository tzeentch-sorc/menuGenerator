package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.entity.Profile;

public interface ProfileService {
    Profile getByUserId(long userId);

    Profile updateByUserId(long userId, String profileStr);
}

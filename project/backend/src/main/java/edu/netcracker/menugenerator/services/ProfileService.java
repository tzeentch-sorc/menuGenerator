package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.entity.Profile;
import edu.netcracker.menugenerator.util.exceptions.ProfileNotValidException;

public interface ProfileService {
    Profile getByUserId(long userId);

    Profile updateByUserId(long userId, String profileStr) throws ProfileNotValidException;
}

package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.dto.response.MessageResponse;
import edu.netcracker.menugenerator.entity.Preference;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


public interface PreferenceService {
    Slice<Preference> getList(long userId, Pageable pageable);

    Preference add(long mealId, long userId, boolean ban);

    MessageResponse remove(long mealId, long userId);
}

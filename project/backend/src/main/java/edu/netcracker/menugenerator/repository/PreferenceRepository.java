package edu.netcracker.menugenerator.repository;

import edu.netcracker.menugenerator.entity.Preference;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    Slice<Preference> findAllByUserId(long userId, Pageable pageable);

    Preference findByMealIdAndUserId(long mealId, long userId);

    void removeByMealIdAndUserId(long mealId, long userId);
}

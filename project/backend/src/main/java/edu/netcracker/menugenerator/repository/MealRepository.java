package edu.netcracker.menugenerator.repository;

import edu.netcracker.menugenerator.util.MealType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.netcracker.menugenerator.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Slice<Meal> findAllByRecipeIgnoreCaseContaining(String recipe, Pageable pageable);

    Slice<Meal> findAllByNameIgnoreCaseContaining(String name, Pageable pageable);

    Slice<Meal> findAllByNameContainingIgnoreCaseAndType(String name, MealType type, Pageable pageable);

    Slice<Meal> findAllByRecipeIgnoreCaseContainingAndType(String name, MealType type, Pageable pageable);

    Slice<Meal> findAllByType(MealType type, Pageable pageable);
}

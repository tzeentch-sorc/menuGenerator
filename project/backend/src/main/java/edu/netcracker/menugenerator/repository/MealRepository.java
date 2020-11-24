package edu.netcracker.menugenerator.repository;

import java.util.List;
import java.util.Optional;

import edu.netcracker.menugenerator.entity.MealType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.netcracker.menugenerator.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Slice<Meal> findAllByRecipeContaining(String recipe, Pageable pageable);

    Slice<Meal> findAllByNameContaining(String name, Pageable pageable);

    Slice<Meal> findAllByNameContainingAndType(String name, MealType type, Pageable pageable);

    Slice<Meal> findAllByRecipeContainingAndType(String name, MealType type, Pageable pageable);
}

package edu.netcracker.recipedb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import edu.netcracker.recipedb.models.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findByName(String name);

    List<Meal> findByNameContaining(String name);
}

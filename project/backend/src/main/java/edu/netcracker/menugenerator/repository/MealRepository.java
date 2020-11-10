package edu.netcracker.menugenerator.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.netcracker.menugenerator.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findByName(String name);

    List<Meal> findByNameContaining(String name);
}

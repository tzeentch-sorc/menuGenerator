package edu.netcracker.menugenerator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.netcracker.menugenerator.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findByName(String name);

    Slice<Meal> findSliceByNameContaining(String name, Pageable pageable);
}

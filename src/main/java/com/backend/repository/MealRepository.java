package com.backend.repository;

import com.backend.domain.Days;
import com.backend.domain.Meal;
import com.backend.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Integer> {

    @EntityGraph(attributePaths = {"user", "recipe"})
    @Override
    List<Meal> findAll();

    void deleteById(int id);

    @EntityGraph(attributePaths = {"user", "recipe"})
    List<Meal> findByUser(User user);
}

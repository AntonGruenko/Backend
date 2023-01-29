package com.backend.services;

import com.backend.domain.Days;
import com.backend.domain.Meal;
import com.backend.domain.User;

import java.util.List;

public interface MealService {
    Meal insert(int userId, int recipeId, int dayId);

    Meal update(int id, int userId, int recipeId);

    Meal getById(int id);

    List<Meal> getByUser(User user);

    List<Meal> getAll();

    List<Meal> getByUserAndDay(User user, Days days);

    void deleteById(int id);
}

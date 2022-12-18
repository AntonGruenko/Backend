package com.backend.services;

import com.backend.domain.Days;
import com.backend.domain.Meal;
import com.backend.domain.Recipe;
import com.backend.domain.User;
import com.backend.repository.MealRepository;
import com.backend.repository.RecipeRepository;
import com.backend.repository.UserRepository;

import java.util.List;

public class MealServiceImpl implements MealService{

    MealRepository mealRepository;
    UserRepository userRepository;
    RecipeRepository recipeRepository;

    @Override
    public Meal insert(int userId, int recipeId) {
        User user = userRepository.findById(userId);
        Recipe recipe = recipeRepository.findById(recipeId);
        Meal meal = Meal.builder()
                .user(user)
                .recipe(recipe)
                .build();
        return mealRepository.save(meal);
    }

    @Override
    public Meal update(int id, int userId, int recipeId) {
        User user = userRepository.findById(userId);
        Recipe recipe = recipeRepository.findById(recipeId);
        Meal meal = Meal.builder()
                .id(id)
                .user(user)
                .recipe(recipe)
                .build();
        return mealRepository.save(meal);
    }

    @Override
    public Meal getById(int id) {
        return mealRepository.getById(id);
    }

    @Override
    public List<Meal> getByUser(User user) {
        return mealRepository.findByUser(user);
    }

    @Override
    public List<Meal> getAll() {
        return mealRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        mealRepository.deleteById(id);
    }
}

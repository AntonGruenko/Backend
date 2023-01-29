package com.backend.services;

import com.backend.domain.Days;
import com.backend.domain.Meal;
import com.backend.domain.Recipe;
import com.backend.domain.User;
import com.backend.repository.DaysRepository;
import com.backend.repository.MealRepository;
import com.backend.repository.RecipeRepository;
import com.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService{

    @Autowired
    MealRepository mealRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    DaysRepository daysRepository;


    @Transactional
    @Override
    public Meal insert(int userId, int recipeId, int dayId) {
        User user = userRepository.findById(userId);
        Recipe recipe = recipeRepository.findById(recipeId);
        Days days = daysRepository.findById(dayId);
        Meal meal = Meal.builder()
                .user(user)
                .recipe(recipe)
                .day(days)
                .build();
        return mealRepository.save(meal);
    }

    @Transactional
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
    public List<Meal> getByUserAndDay(User user, Days days) {
        return mealRepository.findByUserAndDay(user, days);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        mealRepository.deleteById(id);
    }
}

package com.backend.rest.controller;

import com.backend.domain.Days;
import com.backend.domain.Meal;
import com.backend.domain.Recipe;
import com.backend.domain.User;
import com.backend.rest.dto.DaysDto;
import com.backend.rest.dto.MealDto;
import com.backend.services.MealService;
import com.backend.services.RecipeService;
import com.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meals")
public class MealController {
    private final MealService mealService;
    private final UserService userService;
    private final RecipeService recipeService;

    @GetMapping()
    List<MealDto> getMeals(){
        return mealService.getAll().stream().map(MealDto::toDto).collect(Collectors.toList());
    }

    @PostMapping()
    MealDto insertMeal(
            @RequestParam int userId,
            @RequestParam int recipeId){
        Meal meal = mealService.insert(userId, recipeId);
        return MealDto.toDto(meal);
    }

    @PutMapping("/{id}")
    MealDto updateMeal(@PathVariable int id,
                       @RequestParam int userId,
                       @RequestParam int recipeId){
        Meal meal = mealService.update(id, userId, recipeId);
        return MealDto.toDto(meal);
    }

    @GetMapping("/{id}")
    MealDto getMealById(
            @PathVariable int id){

        return MealDto.toDto(mealService.getById(id));
    }

    @GetMapping("/user/{id}")
    List<MealDto> getMealsByUser(@PathVariable int id){
        User user = userService.getById(id);
        return mealService.getByUser(user).stream().map(MealDto::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int id){
        mealService.deleteById(id);
    }
}

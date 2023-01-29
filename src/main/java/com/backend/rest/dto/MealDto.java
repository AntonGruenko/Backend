package com.backend.rest.dto;

import com.backend.domain.Days;
import com.backend.domain.Meal;
import com.backend.domain.Recipe;
import com.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealDto {
    private int id;
    private UserDto user;
    private RecipeDto recipe;
    private DaysDto days;

    public static MealDto toDto(Meal meal) {
        return new MealDto(
                meal.getId(),
                UserDto.toDto(meal.getUser()),
                RecipeDto.toDto(meal.getRecipe()),
                DaysDto.toDto(meal.getDay())
        );
    }

    public static Meal toDomainObject(MealDto mealDto, User user, Recipe recipe, Days days) {
        return new Meal(
                mealDto.getId(),
                user,
                recipe,
                days
        );
    }
}
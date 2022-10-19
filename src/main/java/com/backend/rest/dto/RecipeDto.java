package com.backend.rest.dto;

import com.backend.domain.Recipe;
import com.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDto {
    private int id;
    private String name;
    private UserDto authorDto;
    private String ingredients;
    private String guide;
    private String reccomendations;
    private int time;
    private int kcal;
    private int proteins;
    private int fats;
    private int carbohydrates;
    private int sugar;
    private int likes;
    private int complexity;
    private String tags;

    public static RecipeDto toDto(Recipe recipe) {
        return new RecipeDto(
                recipe.getId(),
                recipe.getName(),
                UserDto.toDto(recipe.getAuthor()),
                recipe.getIngredients(),
                recipe.getGuide(),
                recipe.getReccomendations(),
                recipe.getTime(),
                recipe.getKcal(),
                recipe.getProteins(),
                recipe.getFats(),
                recipe.getCarbohydrates(),
                recipe.getSugar(),
                recipe.getLikes(),
                recipe.getComplexity(),
                recipe.getTags()
        );
    }

    public static Recipe toDomainObject(RecipeDto recipeDto, User author){
        return new Recipe(
                recipeDto.getId(),
                recipeDto.getName(),
                author,
                recipeDto.getIngredients(),
                recipeDto.getGuide(),
                recipeDto.getReccomendations(),
                recipeDto.getTime(),
                recipeDto.getKcal(),
                recipeDto.getProteins(),
                recipeDto.getFats(),
                recipeDto.getCarbohydrates(),
                recipeDto.getSugar(),
                recipeDto.getLikes(),
                recipeDto.getComplexity(),
                recipeDto.getTags()
        );
    }
}

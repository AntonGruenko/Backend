package com.backend.rest.dto;

import com.backend.domain.Post;
import com.backend.domain.Recipe;
import com.backend.domain.RecipeLike;
import com.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeLikeDto {
    private int id;
    private RecipeDto recipeDto;
    private UserDto likerDto;

    public static RecipeLikeDto toDto(RecipeLike recipeLike){
        return new RecipeLikeDto(
                recipeLike.getId(),
                RecipeDto.toDto(recipeLike.getRecipe()),
                UserDto.toDto(recipeLike.getLiker())
        );
    }

    public static RecipeLike toDomainObject(RecipeLike recipeLike, Recipe recipe, User user){
        return new RecipeLike(
                recipeLike.getId(),
                recipe,
                user
        );
    }
}

package com.backend.services;

import com.backend.domain.Recipe;
import com.backend.domain.RecipeLike;

import java.util.List;

public interface RecipeLikeService {
    List<RecipeLike> getAll();

    RecipeLike insert(int recipeId, int userId);


    List<RecipeLike> getByRecipe(Recipe recipe);

    void delete(int recipeId, int userId);

}

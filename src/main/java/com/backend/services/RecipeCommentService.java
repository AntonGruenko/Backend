package com.backend.services;

import com.backend.domain.Recipe;
import com.backend.domain.RecipeComment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecipeCommentService {
    RecipeComment insert(int authorId, Integer recipeId, String text, int likes);

    @Transactional
    RecipeComment update(int id, int authorId, Integer recipeId, String text, int likes);

    List<RecipeComment> getByRecipe(Recipe recipe);

    List<RecipeComment> findByText(String text);

    int incrementLikesById(int id);

    int decrementLikesById(int id);

    List<RecipeComment> getAll();

    void deleteById(int id);
}
package com.backend.services;

import com.backend.domain.Picture;
import com.backend.domain.Recipe;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PictureService {

    @Transactional
    Picture insert(String link, int recipeId, int number);

    @Transactional
    Picture update(int id, String link, int recipeId, int number);

    List<Picture> getAll();

    List<Picture> getByRecipe(Recipe recipe);

    void deleteById(int id);
}

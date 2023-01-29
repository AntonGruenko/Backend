package com.backend.services;

import com.backend.domain.Picture;
import com.backend.domain.Recipe;
import com.backend.repository.PictureRepository;
import com.backend.repository.RecipeRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService{
    @NonNull PictureRepository pictureRepository;
    @NonNull RecipeRepository recipeRepository;

    @Transactional
    @Override
    public Picture insert(String link, int recipeId, int number) {
        Recipe recipe = recipeRepository.getById(recipeId);
        Picture picture = Picture.builder()
                .link(link)
                .recipe(recipe)
                .number(number)
                .build();
        return pictureRepository.save(picture);

    }

    @Transactional
    @Override
    public Picture update(int id, String link, int recipeId, int number) {
        Recipe recipe = recipeRepository.getById(recipeId);
        Picture picture = Picture.builder()
                .id(id)
                .link(link)
                .recipe(recipe)
                .number(number)
                .build();

        return pictureRepository.save(picture);
    }

    @Override
    public List<Picture> getAll() {
        return pictureRepository.findAll();
    }

    @Override
    public List<Picture> getByRecipe(Recipe recipe){ return  pictureRepository.findByRecipeOrderByNumberAsc(recipe);}

    @Override
    public List<Picture> getPreviews() {
        return pictureRepository.findPreviews();
    }

    @Override
    public List<Picture> getPreviewsByUserId(int userId) {
        return pictureRepository.findPreviewsByAuthor(userId);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        pictureRepository.deleteById(id);
    }
}

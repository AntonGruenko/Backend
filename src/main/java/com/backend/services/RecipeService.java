package com.backend.services;

import com.backend.domain.Recipe;
import com.backend.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecipeService {

    Recipe insert(String name, int authorId, String ingredients, String guide, String reccomendations, int time, int kcal, int proteins, int fats, int carbohydrates, int sugar, int likes, int complexity, String tags);

    Recipe update(int id, String name, int authorId, String ingredients, String guide, String reccomendations, int time, int kcal, int proteins, int fats, int carbohydrates, int sugar, int likes, int complexity, String tags);

    Recipe getById(int id);

    List<Recipe> findByName(String name);

    List<Recipe> findByAuthor(User author);

    List<Recipe> findByComplexity(int complexity);

    List<Recipe> getByComplexity(int complexity);

    List<Recipe> getByKcalBetween(int kcal1, int kcal2);

    List<Recipe> getByProteinsBetween(int proteins1, int proteins2);

    List<Recipe> getByFatsBetween(int fats1, int fats2);

    List<Recipe> getByCarbohydratesBetween(int carbohydrates1, int carbohydrates2);

    List<Recipe> getBySugarBetween(int sugar1, int sugar2);

    List<Recipe> getByTimeBetween(int time1, int time2);

    List<Recipe> findByIngredients(String ingredients);

    List<Recipe> findByIngredientsNot(String ingredients);

    List<Recipe> findByTags(String tags);

    List<Recipe> findByTagsNot(String tags);

    int incrementLikes(int id);

    @Transactional
    int decrementLikes(int id);

    List<Recipe> getAll();

    void deleteById(int id);
}

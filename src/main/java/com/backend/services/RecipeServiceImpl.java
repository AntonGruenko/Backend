package com.backend.services;

import com.backend.domain.Recipe;
import com.backend.domain.User;
import com.backend.repository.RecipeRepository;
import com.backend.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{

    @NonNull UserRepository userRepository;
    @NonNull RecipeRepository recipeRepository;

    @Transactional
    @Override
    public Recipe insert(String name, int authorId, String ingredients, String guide, String reccomendations, int time, int kcal, int proteins, int fats, int carbohydrates, int sugar, int likes, int complexity, String tags) {
        User author = userRepository.findById(authorId);
        Recipe recipe = Recipe.builder().
                name(name)
                .author(author)
                .ingredients(ingredients)
                .guide(guide)
                .reccomendations(reccomendations)
                .time(time)
                .kcal(kcal)
                .proteins(proteins)
                .fats(fats)
                .carbohydrates(carbohydrates)
                .sugar(sugar)
                .likes(likes)
                .complexity(complexity)
                .tags(tags)
                .build();
        return recipeRepository.save(recipe);
    }

    @Transactional
    @Override
    public Recipe update(int id, String name, int authorId, String ingredients, String guide, String reccomendations, int time, int kcal, int proteins, int fats, int carbohydrates, int sugar, int likes, int complexity, String tags) {
        Recipe recipe = Recipe.builder()
                .id(id)
                .name(name)
                .author(userRepository.findById(authorId))
                .ingredients(ingredients)
                .guide(guide)
                .reccomendations(reccomendations)
                .time(time)
                .kcal(kcal)
                .proteins(proteins)
                .fats(fats)
                .carbohydrates(carbohydrates)
                .sugar(sugar)
                .likes(likes)
                .complexity(complexity)
                .tags(tags)
                .build();
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe getById(int id) {
        return recipeRepository.getById(id);
    }

    @Override
    public List<Recipe> findByName(String name) {
        return recipeRepository.findByName(name);
    }

    @Override
    public List<Recipe> findByAuthor(User author) {
        return recipeRepository.findByAuthor(author);
    }

    @Override
    public List<Recipe> findByComplexity(int complexity) {
        return recipeRepository.findByComplexity(complexity);
    }

    @Override
    public List<Recipe> getByComplexity(int complexity) {
        return recipeRepository.findByComplexityGreaterThanEqual(complexity);
    }

    @Override
    public List<Recipe> getByKcalBetween(int kcal1, int kcal2) {
        return recipeRepository.findByKcalBetween(kcal1, kcal2);
    }

    @Override
    public List<Recipe> getByProteinsBetween(int proteins1, int proteins2) {
        return recipeRepository.findByProteinsBetween(proteins1, proteins2);
    }

    @Override
    public List<Recipe> getByFatsBetween(int fats1, int fats2) {
        return recipeRepository.findByFatsBetween(fats1, fats2);
    }

    @Override
    public List<Recipe> getByCarbohydratesBetween(int carbohydrates1, int carbohydrates2) {
        return recipeRepository.findByCarbohydratesBetween(carbohydrates1, carbohydrates2);
    }

    @Override
    public List<Recipe> getBySugarBetween(int sugar1, int sugar2) {
        return recipeRepository.findBySugarBetween(sugar1, sugar2);
    }

    @Override
    public List<Recipe> getByTimeBetween(int time1, int time2) {
        return recipeRepository.findByTimeBetween(time1, time2);
    }

    @Override
    public List<Recipe> findByIngredients(String ingredients) {
        return recipeRepository.findByIngredientsContains(ingredients);
    }

    @Override
    public List<Recipe> findByIngredientsNot(String ingredients) {
        return recipeRepository.findByIngredientsNotContains(ingredients);
    }

    @Override
    public List<Recipe> findByTags(String tags) {
        return recipeRepository.findByTagsContains(tags);
    }

    @Override
    public List<Recipe> findByTagsNot(String tags) {
        return recipeRepository.findByTagsNotContains(tags);
    }

    @Transactional
    @Override
    public int incrementLikes(int id) {
        return recipeRepository.incrementRecipeLikesById(id);
    }

    @Transactional
    @Override
    public int decrementLikes(int id) {
        return recipeRepository.decrementRecipeLikesById(id);
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        recipeRepository.deleteById(id);
    }
}

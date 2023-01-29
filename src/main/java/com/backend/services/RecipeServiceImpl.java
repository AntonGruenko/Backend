package com.backend.services;

import com.backend.domain.Recipe;
import com.backend.domain.User;
import com.backend.repository.RecipeRepository;
import com.backend.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{

    @NonNull UserRepository userRepository;
    @NonNull RecipeRepository recipeRepository;

    @Transactional
    @Override
    public Recipe insert(String name, int authorId, String ingredients, String guide, String reccomendations, int time, int kcal, int proteins, int fats, int carbohydrates, int sugar, int complexity, String tags) {
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
                .complexity(complexity)
                .tags(tags)
                .build();
        return recipeRepository.save(recipe);
    }

    @Transactional
    @Override
    public Recipe update(int id, String name, int authorId, String ingredients, String guide, String reccomendations, int time, int kcal, int proteins, int fats, int carbohydrates, int sugar, int complexity, String tags) {
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
        return recipeRepository.findByNameContains(name);
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
    public List<Recipe> findByIngredients(String[] ingredients) {
        List<Recipe> recipes = new ArrayList<>();
        for(int i = 0; i < ingredients.length; ++i){
            recipes.addAll(recipeRepository.findByIngredientsContains(ingredients[i]));
        }

        for (int i = 0; i < recipes.size(); i++) {
            for(int j = i + 1; j < recipes.size(); ++j){
                if(recipes.get(i).getId() == recipes.get(j).getId()){
                    recipes.remove(j);
                }
            }
        }
        return recipes;
    }

    @Override
    public List<Recipe> findByIngredientsNot(String[] ingredients) {
        List<Recipe> recipes = new ArrayList<>();
        for(int i = 0; i < ingredients.length; ++i){
            recipes.addAll(recipeRepository.findByIngredientsNotContains(ingredients[i]));
        }

        for (int i = 0; i < recipes.size(); i++) {
            for(int j = i + 1; j < recipes.size(); ++j){
                if(recipes.get(i).getId() == recipes.get(j).getId()){
                    recipes.remove(j);
                }
            }
        }
        return recipes;
    }

    @Override
    public List<Recipe> findByTags(String[] tags) {
        List<Recipe> recipes = new ArrayList<>();
        for(int i = 0; i < tags.length; ++i){
            recipes.addAll(recipeRepository.findByTagsContains(tags[i]));
        }

        for (int i = 0; i < recipes.size(); i++) {
            for(int j = i + 1; j < recipes.size(); ++j){
                if(recipes.get(i).getId() == recipes.get(j).getId()){
                    recipes.remove(j);
                }
            }
        }
        return recipes;
    }

    @Override
    public List<Recipe> findByTagsNot(String[] tags) {
        List<Recipe> recipes = new ArrayList<>();
        for(int i = 0; i < tags.length; ++i){
            recipes.addAll(recipeRepository.findByTagsNotContains(tags[i]));
        }

        for (int i = 0; i < recipes.size(); i++) {
            for(int j = i + 1; j < recipes.size(); ++j){
                if(recipes.get(i).getId() == recipes.get(j).getId()){
                    recipes.remove(j);
                }
            }
        }
        return recipes;
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

    @Override
    public List<Recipe> findByAuthors(List<User> users) {
        return recipeRepository.findByAuthors(users);
    }
}

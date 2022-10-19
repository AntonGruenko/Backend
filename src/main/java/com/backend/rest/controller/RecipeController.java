package com.backend.rest.controller;

import com.backend.domain.Recipe;
import com.backend.domain.User;
import com.backend.rest.dto.RecipeDto;
import com.backend.rest.dto.UserDto;
import com.backend.services.RecipeService;
import com.backend.services.UserService;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    @NonNull private final RecipeService recipeService;
    @NonNull private final UserService userService;

    @GetMapping()
    List<RecipeDto> getPost() {
        return recipeService.getAll().stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @PostMapping
    RecipeDto insertRecipe(
            @RequestParam String name,
            @RequestParam int authorId,
            @RequestParam String ingredients,
            @RequestParam String guide,
            @RequestParam String reccomendations,
            @RequestParam int time,
            @RequestParam int kcal,
            @RequestParam int proteins,
            @RequestParam int fats,
            @RequestParam int carbohydrates,
            @RequestParam int sugar,
            @RequestParam int likes,
            @RequestParam int complexity,
            @RequestParam String tags) {
        Recipe recipe = recipeService.insert(
                name,
                authorId,
                ingredients,
                guide,
                reccomendations,
                time,
                kcal,
                proteins,
                fats,
                carbohydrates,
                sugar,
                likes,
                complexity,
                tags);

        return RecipeDto.toDto(recipe);
        }

    @PutMapping("/{id}")
    RecipeDto updateRecipe(
            @PathVariable int id,
            @RequestParam String name,
            @RequestParam int authorId,
            @RequestParam String ingredients,
            @RequestParam String guide,
            @RequestParam String reccomendations,
            @RequestParam int time,
            @RequestParam int kcal,
            @RequestParam int proteins,
            @RequestParam int fats,
            @RequestParam int carbohydrates,
            @RequestParam int sugar,
            @RequestParam int likes,
            @RequestParam int complexity,
            @RequestParam String tags) {
        Recipe recipe = recipeService.update(
                id,
                name,
                authorId,
                ingredients,
                guide,
                reccomendations,
                time,
                kcal,
                proteins,
                fats,
                carbohydrates,
                sugar,
                likes,
                complexity,
                tags);

        return RecipeDto.toDto(recipe);
    }

    @GetMapping("/{id}")
    RecipeDto getById(@PathVariable int id){
        Recipe recipe = recipeService.getById(id);
        return RecipeDto.toDto(recipe);
    }

    @GetMapping("/name/{name}")
    List<RecipeDto> getByName(@PathVariable String name){
        List<Recipe> recipeList = recipeService.findByName(name);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/author/{id}")
    List<RecipeDto> getByAuthor(@PathVariable int id){
        List<Recipe> recipeList = recipeService.findByAuthor(userService.getById(id));
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/complexity/higher/{complexity}")
    List<RecipeDto> getByComplexityHigher(@PathVariable int complexity){
        List<Recipe> recipeList = recipeService.getByComplexity(complexity);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/complexity/{complexity}")
    List<RecipeDto> getByComplexity(@PathVariable int complexity){
        List<Recipe> recipeList = recipeService.findByComplexity(complexity);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/kcal/{bottom}/{top}")
    List<RecipeDto> getByKcalBetween(
            @PathVariable int bottom,
            @PathVariable int top){
        List<Recipe> recipeList = recipeService.getByKcalBetween(bottom, top);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/proteins/{bottom}/{top}")
    List<RecipeDto> getByProteinsBetween(
            @PathVariable int bottom,
            @PathVariable int top){
        List<Recipe> recipeList = recipeService.getByProteinsBetween(bottom, top);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/fats/{bottom}/{top}")
    List<RecipeDto> getByFatsBetween(
            @PathVariable int bottom,
            @PathVariable int top){
        List<Recipe> recipeList = recipeService.getByFatsBetween(bottom, top);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/carbohydrates/{bottom}/{top}")
    List<RecipeDto> getByCarbohydratesBetween(
            @PathVariable int bottom,
            @PathVariable int top){
        List<Recipe> recipeList = recipeService.getByCarbohydratesBetween(bottom, top);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/sugar/{bottom}/{top}")
    List<RecipeDto> getBySugarBetween(
            @PathVariable int bottom,
            @PathVariable int top){
        List<Recipe> recipeList = recipeService.getBySugarBetween(bottom, top);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/time/{bottom}/{top}")
    List<RecipeDto> getByTimeBetween(
            @PathVariable int bottom,
            @PathVariable int top){
        List<Recipe> recipeList = recipeService.getByTimeBetween(bottom, top);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/ingredients/{ingredients}")
    List<RecipeDto> getByIngredients(@PathVariable String ingredients){
        List<Recipe> recipeList = recipeService.findByIngredients(ingredients);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/ingredients/not/{ingredients}")
    List<RecipeDto> getByIngredientsNot(@PathVariable String ingredients){
        List<Recipe> recipeList = recipeService.findByIngredientsNot(ingredients);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/tags/{tags}")
    List<RecipeDto> getByTags(@PathVariable String tags){
        List<Recipe> recipeList = recipeService.findByTags(tags);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/tags/not/{tags}")
    List<RecipeDto> getByTagsNot(@PathVariable String tags){
        List<Recipe> recipeList = recipeService.findByTagsNot(tags);
        return recipeList.stream().map(RecipeDto::toDto).collect(Collectors.toList());
    }

    @PostMapping("/increment/{id}")
    void incrementLikesById(@PathVariable int id){
        recipeService.incrementLikes(id);
    }

    @PostMapping("/decrement/{id}")
    void decrementLikesById(@PathVariable int id){
        recipeService.decrementLikes(id);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int id){
        recipeService.deleteById(id);
    }




}

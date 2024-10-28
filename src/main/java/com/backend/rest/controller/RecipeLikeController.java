package com.backend.rest.controller;

import com.backend.domain.Post;
import com.backend.domain.Recipe;
import com.backend.domain.RecipeLike;
import com.backend.domain.User;
import com.backend.rest.dto.PostDto;
import com.backend.rest.dto.RecipeLikeDto;
import com.backend.services.RecipeLikeService;
import com.backend.services.RecipeService;
import com.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipeLikes")
public class RecipeLikeController {

    private final RecipeLikeService recipeLikeService;
    private final RecipeService recipeService;
    private final UserService userService;

    @GetMapping
    List<RecipeLikeDto> getAll(){
        return recipeLikeService.getAll().stream().map(RecipeLikeDto::toDto).collect(Collectors.toList());
    }

    @PostMapping()
    RecipeLikeDto insertRecipeLike(
            @RequestParam int recipeId,
            @RequestParam int likerId){
        RecipeLike recipeLike = recipeLikeService.insert(recipeId, likerId);
        return RecipeLikeDto.toDto(recipeLike);
    }

    @GetMapping("/{recipeId}")
    int getByRecipe(@PathVariable int recipeId){
        Recipe recipe = recipeService.getById(recipeId);
        return recipeLikeService.getByRecipe(recipe).stream().map(RecipeLikeDto::toDto).collect(Collectors.toList()).size();
    }

    @GetMapping("/checkLike")
    boolean checkUserLiked(@RequestParam int recipeId,
                           @RequestParam int userId){
        Recipe recipe = recipeService.getById(recipeId);
        User user = userService.getById(userId);
        List<RecipeLike> likes = recipeLikeService.getByRecipe(recipe);

        for(RecipeLike like : likes) {
            if (like.getLiker().getId() == user.getId()) {
                return true;
            }
        }

        return false;

    }

    @DeleteMapping("/{recipeId}/{userId}")
    void deleteById(@PathVariable int recipeId,
                    @PathVariable int userId){
        recipeLikeService.delete(recipeId, userId);
    }
}

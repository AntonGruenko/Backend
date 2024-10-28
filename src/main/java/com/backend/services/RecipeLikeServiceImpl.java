package com.backend.services;

import com.backend.domain.Recipe;
import com.backend.domain.RecipeLike;
import com.backend.domain.User;
import com.backend.repository.RecipeLikeRepository;
import com.backend.repository.RecipeRepository;
import com.backend.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeLikeServiceImpl implements RecipeLikeService{

    @NonNull private RecipeLikeRepository recipeLikeRepository;
    @NonNull private RecipeRepository recipeRepository;
    @NonNull private UserRepository userRepository;

    @Override
    public List<RecipeLike> getAll() {
        return recipeLikeRepository.findAll();
    }

    @Transactional
    @Override
    public RecipeLike insert(int recipeId, int userId) {
        Recipe recipe = recipeRepository.getById(recipeId);
        User user = userRepository.getById(userId);
        RecipeLike recipeLike = RecipeLike.builder()
                .recipe(recipe)
                .liker(user)
                .build();
        return recipeLikeRepository.save(recipeLike);
    }

    @Override
    public List<RecipeLike> getByRecipe(Recipe recipe) {
        return recipeLikeRepository.findByRecipe(recipe);
    }

    @Transactional
    @Override
    public void delete(int recipeId, int userId) {
        Recipe recipe = recipeRepository.getById(recipeId);
        List<RecipeLike> recipeLikes = recipeLikeRepository.findByRecipe(recipe);
        for (RecipeLike recipeLike : recipeLikes) {
            if (recipeLike.getLiker().getId() == userId) {
                recipeLikeRepository.deleteById(recipeLike.getId());
            }
        }
    }
}

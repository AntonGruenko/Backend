package com.backend.services;

import com.backend.domain.*;
import com.backend.repository.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeCommentServiceImpl implements RecipeCommentService {
    @NonNull RecipeCommentRepository commentRepository;
    @NonNull UserRepository userRepository;
    @NonNull RecipeRepository recipeRepository;

    @Transactional
    @Override
    public RecipeComment insert(int authorId, Integer recipeId, String text, int likes) {
        User author = userRepository.findById(authorId);
        Recipe recipe = recipeRepository.getById(recipeId);

        RecipeComment recipeComment = RecipeComment.builder()
                .author(author)
                .recipe(recipe)
                .text(text)
                .likes(likes)
                .build();
        return commentRepository.save(recipeComment);
    }

    @Transactional
    @Override
    public RecipeComment update(int id, int authorId, Integer recipeId, String text, int likes) {
        RecipeComment recipeComment = RecipeComment.builder()
                .id(id)
                .author(userRepository.findById(authorId))
                .recipe(recipeRepository.getById(recipeId))
                .text(text)
                .likes(likes)
                .build();

        return commentRepository.save(recipeComment);
    }

    @Override
    public List<RecipeComment> getByRecipe(Recipe recipe) {
        return commentRepository.findByRecipe(recipe);
    }

    @Override
    public List<RecipeComment> findByText(String text) {
        return commentRepository.findByTextContains(text);
    }

    @Transactional
    @Override
    public int incrementLikesById(int id) {
        return commentRepository.incrementCommentLikesById(id);
    }

    @Transactional
    @Override
    public int decrementLikesById(int id) {
        return commentRepository.decrementCommentLikesById(id);
    }

    @Override
    public List<RecipeComment> getAll() {
        return commentRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }
}

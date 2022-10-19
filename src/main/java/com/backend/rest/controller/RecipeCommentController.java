package com.backend.rest.controller;

import com.backend.domain.PostComment;
import com.backend.domain.RecipeComment;
import com.backend.rest.dto.PostCommentDto;
import com.backend.rest.dto.RecipeCommentDto;
import com.backend.services.PostCommentService;
import com.backend.services.PostService;
import com.backend.services.RecipeCommentService;
import com.backend.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipeComment")
public class RecipeCommentController {

    private final RecipeCommentService recipeCommentService;
    private final RecipeService recipeService;

    @GetMapping()
    List<RecipeCommentDto> getComment() {
        return recipeCommentService.getAll().stream().map(RecipeCommentDto::toDto).collect(Collectors.toList());
    }

    @PostMapping()
    RecipeCommentDto insertComment(
            @RequestParam int authorId,
            @RequestParam int recipeId,
            @RequestParam String text,
            @RequestParam int likes
    ) {
        RecipeComment recipeComment;
        recipeComment = recipeCommentService.insert(
                authorId,
                recipeId,
                text,
                likes);
        return RecipeCommentDto.toDto(recipeComment);
    }

    @PutMapping("/{id}")
    RecipeCommentDto updateComment(
            @PathVariable int id,
            @RequestParam int authorId,
            @RequestParam int recipeId,
            @RequestParam String text,
            @RequestParam int likes
    ) {
        RecipeComment recipeComment = recipeCommentService.update(
                id,
                authorId,
                recipeId,
                text,
                likes);

        return RecipeCommentDto.toDto(recipeComment);
    }

    @GetMapping("/recipe/{id}")
    List<RecipeCommentDto> getByRecipeId(@PathVariable int id) {
        List<RecipeComment> recipeCommentList = recipeCommentService.getByRecipe(recipeService.getById(id));
        return recipeCommentList.stream().map(RecipeCommentDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/text/{text}")
    List<RecipeCommentDto> getByText(@PathVariable String text) {
        List<RecipeComment> recipeCommentList = recipeCommentService.findByText(text);
        return recipeCommentList.stream().map(RecipeCommentDto::toDto).collect(Collectors.toList());
    }

    @PostMapping("/increment/{id}")
    void incrementLikesById(@PathVariable int id) {
        recipeCommentService.incrementLikesById(id);
    }

    @PostMapping("/decrement/{id}")
    void decrementLikesById(@PathVariable int id) {
        recipeCommentService.decrementLikesById(id);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int id){
        recipeCommentService.deleteById(id);
    }
}


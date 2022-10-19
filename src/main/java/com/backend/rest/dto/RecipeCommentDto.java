package com.backend.rest.dto;

import com.backend.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeCommentDto {
    private int id;
    private UserDto author;
    private RecipeDto recipe;
    private String text;
    private int likes;

    public static RecipeCommentDto toDto(RecipeComment recipeComment){
        return new RecipeCommentDto(
                recipeComment.getId(),
                UserDto.toDto(recipeComment.getAuthor()),
                RecipeDto.toDto(recipeComment.getRecipe()),
                recipeComment.getText(),
                recipeComment.getLikes()
        );
    }

    public static RecipeComment toDomainObject(RecipeCommentDto commentDto, User author, Recipe recipe){
        return new RecipeComment(
                commentDto.getId(),
                author,
                recipe,
                commentDto.getText(),
                commentDto.getLikes()
        );
    }
}

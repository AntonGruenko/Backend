package com.backend.rest.dto;

import com.backend.domain.Picture;
import com.backend.domain.Recipe;
import com.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureDto {
    private int id;
    private String link;
    private RecipeDto recipe;
    private int number;

    public static PictureDto toDto(Picture picture){
        return new PictureDto(
                picture.getId(),
                picture.getLink(),
                RecipeDto.toDto(picture.getRecipe()),
                picture.getNumber()
        );
    }

    public static Picture toDomainObject(PictureDto pictureDto, Recipe recipe){
        return new Picture(
                pictureDto.getId(),
                pictureDto.getLink(),
                recipe,
                pictureDto.getNumber()
        );
    }
}

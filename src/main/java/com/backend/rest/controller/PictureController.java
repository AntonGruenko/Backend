package com.backend.rest.controller;

import com.backend.domain.Picture;
import com.backend.domain.Recipe;
import com.backend.domain.User;
import com.backend.rest.dto.PictureDto;
import com.backend.rest.dto.PostDto;
import com.backend.rest.dto.RecipeDto;
import com.backend.services.PictureService;
import com.backend.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {
    private final PictureService pictureService;
    private final RecipeService recipeService;

    @GetMapping()
    List<PictureDto> getPicture(){
        return pictureService.getAll().stream().map(PictureDto::toDto).collect(Collectors.toList());
    }

    @PostMapping()
    PictureDto insertPicture(
            @RequestParam String link,
            @RequestParam int recipeId,
            @RequestParam int number){
        Picture picture = pictureService.insert(link, recipeId, number);
        return PictureDto.toDto(picture);
    }

    @GetMapping("/recipe/{id}")
    List<PictureDto> findByRecipe(
            @PathVariable int id){
        Recipe recipe = recipeService.getById(id);
        return pictureService.getByRecipe(recipe).stream().map(PictureDto::toDto).collect(Collectors.toList());
    }
    @PutMapping("/{id}")
    PictureDto updatePicture(
            @PathVariable int id,
            @RequestParam String link,
            @RequestParam int recipeId,
            @RequestParam int number){
        Picture picture = pictureService.update(id, link, recipeId, number);
        return PictureDto.toDto(picture);
    }

    @DeleteMapping("/{id}")
    void deletePictureById(@PathVariable int id){
        pictureService.deleteById(id);
    }


}

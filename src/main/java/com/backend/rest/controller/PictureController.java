package com.backend.rest.controller;

import com.backend.domain.Picture;
import com.backend.rest.dto.PictureDto;
import com.backend.services.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {
    private final PictureService pictureService;

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

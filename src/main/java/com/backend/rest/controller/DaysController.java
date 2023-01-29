package com.backend.rest.controller;

import com.backend.domain.Days;
import com.backend.domain.Post;
import com.backend.domain.User;
import com.backend.rest.dto.DaysDto;
import com.backend.rest.dto.PostDto;
import com.backend.services.DaysService;
import com.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/days")
public class DaysController {
    private final DaysService daysService;
    private final UserService userService;

    @GetMapping()
    List<DaysDto> getDays(){
        return daysService.getAll().stream().map(DaysDto::toDto).collect(Collectors.toList());
    }

    @PostMapping()
    DaysDto insertDays(
            @RequestParam int userId,
            @RequestParam int day,
            @RequestParam int kcal,
            @RequestParam int proteins,
            @RequestParam int fats,
            @RequestParam int carbohydrates,
            @RequestParam boolean isSuccessful){
        Days days = daysService.insert(userId, day, kcal, proteins, fats, carbohydrates, isSuccessful);
        return DaysDto.toDto(days);
    }

    @PutMapping("/{id}")
    DaysDto updatePost(@PathVariable int id,
                       @RequestParam int userId,
                       @RequestParam int day,
                       @RequestParam int kcal,
                       @RequestParam int proteins,
                       @RequestParam int fats,
                       @RequestParam int carbohydrates,
                       @RequestParam boolean isSuccessful){
        Days days = daysService.update(id, userId, day, kcal, proteins, fats, carbohydrates, isSuccessful);
        return DaysDto.toDto(days);
    }

    @GetMapping("/{id}")
    DaysDto getDaysById(
            @PathVariable int id){

        return DaysDto.toDto(daysService.getById(id));
    }

    @GetMapping("/user/{id}")
    List<DaysDto> getDaysByUser(@PathVariable int id){
        User user = userService.getById(id);
        return daysService.getByUser(user).stream().map(DaysDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("user/{userId}/day/{day}")
    DaysDto getDayByUserAndDay(@PathVariable int userId,
                               @PathVariable int day){
        return DaysDto.toDto(daysService.getByUserAndDay(userId, day));
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int id){
        daysService.deleteById(id);
    }
}

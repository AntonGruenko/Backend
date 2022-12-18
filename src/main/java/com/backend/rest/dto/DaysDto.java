package com.backend.rest.dto;

import com.backend.domain.Days;
import com.backend.domain.Post;
import com.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DaysDto {
    private int id;
    private UserDto user;
    private int day;
    private int kcal;
    private int proteins;
    private int fats;
    private int carbohydrates;
    private boolean isSuccessful;

    public static DaysDto toDto(Days days){
        return new DaysDto(
                days.getId(),
                UserDto.toDto(days.getUser()),
                days.getDay(),
                days.getKcal(),
                days.getProteins(),
                days.getFats(),
                days.getCarbohydrates(),
                days.isSuccessful()
        );
    }

    public static Days toDomainObject(DaysDto daysDto, User user){
        return new Days(
                daysDto.getId(),
                user,
                daysDto.getDay(),
                daysDto.getKcal(),
                daysDto.getProteins(),
                daysDto.getFats(),
                daysDto.getCarbohydrates(),
                daysDto.isSuccessful()
        );
    }
}

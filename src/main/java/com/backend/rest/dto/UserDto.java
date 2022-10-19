package com.backend.rest.dto;

import com.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private int id;
    private String name;
    private String email;
    private String password;
    private String status;
    private String profilePic;

    public static UserDto toDto(User user) {

        return new UserDto(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus(),
                user.getProfilePic());
    }

    public static User toDomainObject(UserDto userDto) {

        return new User(userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getStatus(),
                userDto.getProfilePic());
    }
}

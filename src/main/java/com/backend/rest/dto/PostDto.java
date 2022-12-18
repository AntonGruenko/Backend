package com.backend.rest.dto;

import com.backend.domain.Post;
import com.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private int id;
    private UserDto authorDto;
    private String text;
    private String picture;

    public static PostDto toDto(Post post){
        return new PostDto(
                post.getId(),
                UserDto.toDto(post.getAuthor()),
                post.getText(),
                post.getPicture()
        );
    }

    public static Post toDomainObject(PostDto postDto, User user){
        return new Post(
                postDto.getId(),
                user,
                postDto.getText(),
                postDto.getPicture()
        );
    }
}

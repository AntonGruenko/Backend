package com.backend.rest.dto;

import com.backend.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikeDto{
    private int id;
    private PostDto postDto;
    private UserDto likerDto;

    public static PostLikeDto toDto(PostLike postLike){
        return new PostLikeDto(
                postLike.getId(),
                PostDto.toDto(postLike.getPost()),
                UserDto.toDto(postLike.getLiker())
        );
    }

    public static PostLike toDomainObject(PostLike postLike, Post post, User user){
        return new PostLike(
                postLike.getId(),
                post,
                user
        );
    }
}

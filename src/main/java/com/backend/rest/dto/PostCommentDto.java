package com.backend.rest.dto;

import com.backend.domain.PostComment;
import com.backend.domain.Post;
import com.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCommentDto {
    private int id;
    private UserDto author;
    private PostDto post;
    private String text;
    private int likes;

    public static PostCommentDto toDto(PostComment postComment){
        return new PostCommentDto(
                postComment.getId(),
                UserDto.toDto(postComment.getAuthor()),
                PostDto.toDto(postComment.getPost()),
                postComment.getText(),
                postComment.getLikes()
        );
    }

    public static PostComment toDomainObject(PostCommentDto commentDto, User author, Post post){
        return new PostComment(
                commentDto.getId(),
                author,
                post,
                commentDto.getText(),
                commentDto.getLikes()
        );
    }
}

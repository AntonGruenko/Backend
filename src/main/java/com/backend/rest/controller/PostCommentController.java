package com.backend.rest.controller;

import com.backend.domain.PostComment;
import com.backend.rest.dto.PostCommentDto;
import com.backend.services.PostCommentService;
import com.backend.services.PostService;
import com.backend.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postComment")
public class PostCommentController {

    private final PostCommentService postCommentService;
    private final PostService postService;

    @GetMapping()
    List<PostCommentDto> getComment() {
        return postCommentService.getAll().stream().map(PostCommentDto::toDto).collect(Collectors.toList());
    }

    @PostMapping()
    PostCommentDto insertComment(
            @RequestParam int authorId,
            @RequestParam int postId,
            @RequestParam String text,
            @RequestParam int likes
    ) {
        PostComment postComment;
        postComment = postCommentService.insert(
                authorId,
                postId,
                text,
                likes);
        return PostCommentDto.toDto(postComment);
    }

    @PutMapping("/{id}")
    PostCommentDto updateComment(
            @PathVariable int id,
            @RequestParam int authorId,
            @RequestParam int postId,
            @RequestParam String text,
            @RequestParam int likes
    ) {
        PostComment postComment = postCommentService.update(
                id,
                authorId,
                postId,
                text,
                likes);

        return PostCommentDto.toDto(postComment);
    }

    @GetMapping("/post/{id}")
    List<PostCommentDto> getByPostId(@PathVariable int id) {
        List<PostComment> postCommentList = postCommentService.getByPost(postService.getById(id));
        return postCommentList.stream().map(PostCommentDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/text/{text}")
    List<PostCommentDto> getByText(@PathVariable String text) {
        List<PostComment> postCommentList = postCommentService.findByText(text);
        return postCommentList.stream().map(PostCommentDto::toDto).collect(Collectors.toList());
    }

    @PostMapping("/increment/{id}")
    void incrementLikesById(@PathVariable int id) {
        postCommentService.incrementLikesById(id);
    }

    @PostMapping("/decrement/{id}")
    void decrementLikesById(@PathVariable int id) {
        postCommentService.decrementLikesById(id);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int id){
        postCommentService.deleteById(id);
    }
}

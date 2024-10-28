package com.backend.rest.controller;

import com.backend.domain.*;
import com.backend.rest.dto.PostLikeDto;
import com.backend.rest.dto.RecipeLikeDto;
import com.backend.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postLikes")
public class PostLikeController {
    private final PostLikeService postLikeService;
    private final PostService postService;
    private final UserService userService;

    @GetMapping
    List<PostLikeDto> getAll(){
        return postLikeService.getAll().stream().map(PostLikeDto::toDto).collect(Collectors.toList());
    }

    @PostMapping()
    PostLikeDto insertRecipeLike(
            @RequestParam int postId,
            @RequestParam int likerId){
        PostLike postLike = postLikeService.insert(postId, likerId);
        return PostLikeDto.toDto(postLike);
    }

    @GetMapping("/{postId}")
    int getByRecipe(@PathVariable int postId){
        Post post = postService.getById(postId);
        return postLikeService.getByPost(post).stream().map(PostLikeDto::toDto).collect(Collectors.toList()).size();
    }

    @GetMapping("/checkLike")
    boolean checkUserLiked(@RequestParam int postId,
                           @RequestParam int userId){
        Post post = postService.getById(postId);
        User user = userService.getById(userId);
        List<PostLike> likes = postLikeService.getByPost(post);

        for(PostLike like : likes) {
            if (like.getLiker().getId() == user.getId()) {
                return true;
            }
        }

        return false;

    }

    @DeleteMapping("/{postId}/{userId}")
    void deleteById(@PathVariable int postId,
                    @PathVariable int userId){
        postLikeService.delete(postId, userId);
    }
}

package com.backend.rest.controller;

import com.backend.domain.Post;
import com.backend.domain.User;
import com.backend.rest.dto.PostDto;
import com.backend.rest.dto.UserDto;
import com.backend.services.PostService;
import com.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping()
    List<PostDto> getPost(){
        return postService.getAll().stream().map(PostDto::toDto).collect(Collectors.toList());
    }

    @PostMapping()
    PostDto insertPost(
            @RequestParam int authorId,
            @RequestParam String text,
            @RequestParam String picture){
        Post post = postService.insert(authorId, text, picture);
        return PostDto.toDto(post);
    }

    @PutMapping("/{id}")
    PostDto updatePost(@PathVariable int id,
                       @RequestParam int authorId,
                       @RequestParam String text,
                       @RequestParam String picture){
        Post post = postService.update(id, authorId, text, picture);
        return PostDto.toDto(post);
    }

    @GetMapping("/{id}")
    PostDto getPostById(
            @PathVariable int id){

        return PostDto.toDto(postService.getById(id));
    }

    @GetMapping("/author/{id}")
    List<PostDto> getPostByAuthor(@PathVariable int id){
        User user = userService.getById(id);
        return postService.getByAuthor(user).stream().map(PostDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/text/{text}")
    List<PostDto> getPostByText(@PathVariable String text){
        return postService.findByText(text).stream().map(PostDto::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int id){
        postService.deleteById(id);
    }
}

package com.backend.services;

import com.backend.domain.Post;
import com.backend.domain.User;
import com.backend.repository.PostRepository;
import com.backend.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    @NonNull PostRepository postRepository;
    @NonNull UserRepository userRepository;

    @Override
    @Transactional
    public Post insert(int authorId, String text, String picture) {
        User author = (User) userRepository.findById(authorId);
        Post post = Post.builder()
                .author(author)
                .text(text)
                .picture(picture)
                .build();
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public Post update(int id, int authorId, String text, String picture) {
        Post post = Post.builder()
                .id(id)
                .author(userRepository.findById(authorId))
                .text(text)
                .picture(picture)
                .build();

        return postRepository.save(post);
    }

    @Override
    public Post getById(int id) {
        return postRepository.getById(id);
    }

    @Override
    public List<Post> getByAuthor(User author) {
        return postRepository.findByAuthor(author);
    }

    @Override
    public List<Post> findByText(String text) {
        return postRepository.findByTextContains(text);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        postRepository.deleteById(id);
    }
}

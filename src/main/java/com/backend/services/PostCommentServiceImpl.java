package com.backend.services;

import com.backend.domain.PostComment;
import com.backend.domain.Post;
import com.backend.domain.Recipe;
import com.backend.domain.User;
import com.backend.repository.PostCommentRepository;
import com.backend.repository.PostRepository;
import com.backend.repository.RecipeRepository;
import com.backend.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {
    @NonNull PostCommentRepository commentRepository;
    @NonNull UserRepository userRepository;
    @NonNull PostRepository postRepository;

    @Transactional
    @Override
    public PostComment insert(int authorId, Integer postId, String text, int likes) {
        User author = userRepository.findById(authorId);
        Post post = postRepository.getById(postId);

        PostComment postComment = PostComment.builder()
                .author(author)
                .post(post)
                .text(text)
                .likes(likes)
                .build();
        return commentRepository.save(postComment);
    }

    @Transactional
    @Override
    public PostComment update(int id, int authorId, int postId, String text, int likes) {
        PostComment postComment = PostComment.builder()
                .id(id)
                .author(userRepository.findById(authorId))
                .post(postRepository.getById(postId))
                .text(text)
                .likes(likes)
                .build();

        return commentRepository.save(postComment);
    }

    @Override
    public List<PostComment> getByPost(Post post) {
        return commentRepository.findByPost(post);
    }

    @Override
    public List<PostComment> findByText(String text) {
        return commentRepository.findByTextContains(text);
    }

    @Transactional
    @Override
    public int incrementLikesById(int id) {
        return commentRepository.incrementCommentLikesById(id);
    }

    @Transactional
    @Override
    public int decrementLikesById(int id) {
        return commentRepository.decrementCommentLikesById(id);
    }

    @Override
    public List<PostComment> getAll() {
        return commentRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }
}

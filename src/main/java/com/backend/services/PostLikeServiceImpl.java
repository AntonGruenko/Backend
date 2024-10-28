package com.backend.services;

import com.backend.domain.*;
import com.backend.repository.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {
    @NonNull private PostLikeRepository postLikeRepository;
    @NonNull private PostRepository postRepository;
    @NonNull private UserRepository userRepository;

    @Override
    public List<PostLike> getAll() {
        return postLikeRepository.findAll();
    }

    @Transactional
    @Override
    public PostLike insert(int postId, int userId) {
        Post post = postRepository.getById(postId);
        User user = userRepository.getById(userId);
        PostLike postLike = PostLike.builder()
                .post(post)
                .liker(user)
                .build();
        return postLikeRepository.save(postLike);
    }

    @Override
    public List<PostLike> getByPost(Post post) {
        return postLikeRepository.findByPost(post);
    }

    @Transactional
    @Override
    public void delete(int postId, int userId) {
        Post post = postRepository.getById(postId);
        List<PostLike> postLikes = postLikeRepository.findByPost(post);
        for (PostLike postLike : postLikes) {
            if (postLike.getLiker().getId() == userId) {
                postLikeRepository.deleteById(postLike.getId());
            }
        }
    }
}

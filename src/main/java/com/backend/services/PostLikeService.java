package com.backend.services;

import com.backend.domain.Post;
import com.backend.domain.PostLike;

import java.util.List;

public interface PostLikeService {
    List<PostLike> getAll();

    PostLike insert(int postId, int userId);

    List<PostLike> getByPost(Post post);

    void delete(int postId, int userId);
}

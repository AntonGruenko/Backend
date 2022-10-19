package com.backend.services;

import com.backend.domain.PostComment;
import com.backend.domain.Post;
import com.backend.domain.Recipe;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostCommentService {
    PostComment insert(int authorId, Integer postId, String text, int likes);

    @Transactional
    PostComment update(int id, int authorId, int postId, String text, int likes);

    List<PostComment> getByPost(Post post);

    List<PostComment> findByText(String text);

    int incrementLikesById(int id);

    int decrementLikesById(int id);

    List<PostComment> getAll();

    void deleteById(int id);
}

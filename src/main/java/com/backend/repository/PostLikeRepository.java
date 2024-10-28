package com.backend.repository;

import com.backend.domain.Post;
import com.backend.domain.PostLike;
import com.backend.domain.Recipe;
import com.backend.domain.RecipeLike;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    @EntityGraph(attributePaths = {"post", "liker"})
    @Override
    List<PostLike> findAll();

    @EntityGraph(attributePaths = {"post", "liker"})
    List<PostLike> findByPost(Post post);

    void deleteById(int id);
}

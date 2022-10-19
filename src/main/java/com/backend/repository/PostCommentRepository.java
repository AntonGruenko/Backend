package com.backend.repository;

import com.backend.domain.PostComment;
import com.backend.domain.Post;
import com.backend.domain.Recipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {

    @Override
    @EntityGraph(attributePaths = {"author", "post"})
    List<PostComment> findAll();

    @EntityGraph(attributePaths = "post")
    List<PostComment> findByPost(Post post);

    List<PostComment> findByTextContains(String text);

    @Modifying
    @Query("delete from PostComment where id = :id")
    void deleteById(int id);

    @Modifying
    @Query("update PostComment c set c.likes = c.likes + 1 where c.id = :id")
    int incrementCommentLikesById(@Param("id") int id);

    @Modifying
    @Query("update PostComment c set c.likes = c.likes - 1 where c.id = :id")
    int decrementCommentLikesById(@Param("id") int id);
}

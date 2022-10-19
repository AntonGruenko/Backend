package com.backend.repository;

import com.backend.domain.Post;
import com.backend.domain.PostComment;
import com.backend.domain.Recipe;
import com.backend.domain.RecipeComment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeCommentRepository extends JpaRepository<RecipeComment, Integer> {

    @Override
    @EntityGraph(attributePaths = {"author", "recipe"})
    List<RecipeComment> findAll();

    @EntityGraph(attributePaths = "recipe")
    List<RecipeComment> findByRecipe(Recipe recipe);

    List<RecipeComment> findByTextContains(String text);

    @Modifying
    @Query("delete from RecipeComment where id = :id")
    void deleteById(int id);

    @Modifying
    @Query("update RecipeComment c set c.likes = c.likes + 1 where c.id = :id")
    int incrementCommentLikesById(@Param("id") int id);

    @Modifying
    @Query("update RecipeComment c set c.likes = c.likes - 1 where c.id = :id")
    int decrementCommentLikesById(@Param("id") int id);
}

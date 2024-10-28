package com.backend.repository;

import com.backend.domain.Picture;
import com.backend.domain.Recipe;
import com.backend.domain.RecipeLike;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeLikeRepository extends JpaRepository<RecipeLike, Integer> {

    @EntityGraph(attributePaths = {"recipe", "liker"})
    @Override
    List<RecipeLike> findAll();

    @EntityGraph(attributePaths = {"recipe", "liker"})
    List<RecipeLike> findByRecipe(Recipe recipe);

    void deleteById(int id);

}

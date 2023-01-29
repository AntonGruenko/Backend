package com.backend.repository;

import com.backend.domain.Picture;
import com.backend.domain.Recipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Integer> {

    @EntityGraph(attributePaths = {"recipe"})
    @Override
    List<Picture> findAll();

    @EntityGraph(attributePaths = "recipe")
    List<Picture> findByRecipeOrderByNumberAsc(Recipe recipe);

    void deleteById(int id);

    @Modifying
    @Query("update Picture p set p.link = :link where p.id = :id")
    void updateLinkById(@Param("id") int id, 
                        @Param("link") String link);

    @Query("select p from Picture p where p.number =0")
    List<Picture> findPreviews();

    @Query("select p from Picture p where p.number =0 and p.recipe.author.id = :id")
    List<Picture> findPreviewsByAuthor(@Param("id") int id);


}

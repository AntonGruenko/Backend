package com.backend.repository;

import com.backend.domain.Recipe;
import com.backend.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @EntityGraph(attributePaths = "author")
    @Override
    List<Recipe> findAll();

    Recipe findById(int id);

    @EntityGraph(attributePaths = "author")
    List<Recipe> findByAuthor(User author);

    List<Recipe> findByName(String name);

    List<Recipe> findByComplexity(int complexity);

    List<Recipe> findByComplexityGreaterThanEqual(int complexity);

    List<Recipe> findByKcalBetween(int kcal1, int kcal2);

    List<Recipe> findByProteinsBetween(int proteins1, int proteins2);

    List<Recipe> findByFatsBetween(int fats1, int fats2);

    List<Recipe> findByCarbohydratesBetween(int carbohydrates1, int carbohydrates2);

    List<Recipe> findBySugarBetween(int sugar1, int sugar2);

    List<Recipe> findByTimeBetween(int time1, int time2);

    List<Recipe> findByIngredientsContains(String ingredients);

    List<Recipe> findByTagsContains(String tags);

    List<Recipe> findByIngredientsNotContains(String ingredients);

    List<Recipe> findByTagsNotContains(String tags);

    void deleteById(int id);

    @Modifying
    @Query("update Recipe r set r.kcal = :kcal, r.proteins = :proteins, r.fats = :fats, r.carbohydrates = :carbohydrates, r.sugar = :sugar where r.id = :id")
    void updateRecipeKcalAndProteinsAndFatsAndCarbohydratesAndSugarById(@Param("id") int id,
                                                                        @Param("kcal") int kcal,
                                                                        @Param("proteins") int proteins,
                                                                        @Param("fats") int fats,
                                                                        @Param("carbohydrates") int carbohydrates,
                                                                        @Param("sugar") int sugar);

    @Modifying
    @Query("update Recipe r set r.likes = r.likes + 1 where r.id = :id")
    int incrementRecipeLikesById(@Param("id") int id);

    @Modifying
    @Query("update Recipe r set r.likes = r.likes - 1 where r.id = :id")
    int decrementRecipeLikesById(@Param("id") int id);


}

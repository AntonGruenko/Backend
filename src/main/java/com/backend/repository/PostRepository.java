package com.backend.repository;

import com.backend.domain.Post;
import com.backend.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @EntityGraph(attributePaths = "author")
    @Override
    List<Post> findAll();

    void deleteById(int id);

    @EntityGraph(attributePaths = "author")
    List<Post> findByAuthor(User author);

    List<Post> findByTextContains(String text);
}

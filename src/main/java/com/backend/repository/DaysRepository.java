package com.backend.repository;

import com.backend.domain.Days;
import com.backend.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaysRepository extends JpaRepository<Days, Integer> {

    @EntityGraph(attributePaths = "user")
    @Override
    List<Days> findAll();

    Days findById(int id);

    void deleteById(int id);

    Days findByUserAndDay(User user, int day);

    @EntityGraph(attributePaths = "user")
    List<Days> findByUser(User user);
}

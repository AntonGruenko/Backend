package com.backend.repository;

import com.backend.domain.Subscription;
import com.backend.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    @EntityGraph(attributePaths = {"follower", "leader"})
    @Override
    List<Subscription> findAll();

    void deleteById(int id);

    @EntityGraph(attributePaths = "follower")
    List<Subscription> findByFollower(User follower);

    @EntityGraph(attributePaths = "leader")
    List<Subscription> findByLeader(User leader);
}

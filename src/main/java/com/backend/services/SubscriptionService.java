package com.backend.services;

import com.backend.domain.Subscription;
import com.backend.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubscriptionService {

    @Transactional
    Subscription insert(int leaderId, int followerId);

    @Transactional
    Subscription update(int id, int leaderId, int followerId);

    List<Subscription> findByLeader(User leader);

    List<Subscription> findByFollower(User follower);

    List<Subscription> getAll();

    void deleteById(int id);
}

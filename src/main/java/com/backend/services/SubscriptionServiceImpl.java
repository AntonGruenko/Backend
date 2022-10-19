package com.backend.services;

import com.backend.domain.Subscription;
import com.backend.domain.User;
import com.backend.repository.SubscriptionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{
    @NonNull SubscriptionRepository subscriptionRepository;
    @NonNull UserService userService;

    @Override
    @Transactional
    public Subscription insert(int leaderId, int followerId) {
        User leader = userService.getById(leaderId);
        User follower = userService.getById(followerId);
        Subscription subscription = Subscription.builder()
                .leader(leader)
                .follower(follower)
                .build();
        return subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public Subscription update(int id, int leaderId, int followerId) {
        User leader = userService.getById(leaderId);
        User follower = userService.getById(followerId);
        Subscription subscription = Subscription.builder()
                .id(id)
                .leader(leader)
                .follower(follower)
                .build();
        return subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> findByLeader(User leader) {
        return subscriptionRepository.findByLeader(leader);
    }

    @Override
    public List<Subscription> findByFollower(User follower) {
        return subscriptionRepository.findByFollower(follower);
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        subscriptionRepository.deleteById(id);
    }
}

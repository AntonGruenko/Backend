package com.backend.rest.controller;

import com.backend.domain.Subscription;
import com.backend.domain.User;
import com.backend.rest.dto.SubscriptionDto;
import com.backend.services.SubscriptionService;
import com.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final UserService userService;

    @GetMapping()
    List<SubscriptionDto> getSubscription(){
        return subscriptionService.getAll().stream().map(SubscriptionDto::toDto).collect(Collectors.toList());

    }

    @PostMapping()
    SubscriptionDto insertSubscription(
            @RequestParam int leaderId,
            @RequestParam int followerId){
        Subscription subscription = subscriptionService.insert(leaderId, followerId);
        return SubscriptionDto.toDto(subscription);
    }

    @PutMapping("/{id}")
    SubscriptionDto updateSubscription(
            @PathVariable int id,
            @RequestParam int leaderId,
            @RequestParam int followerId){
        Subscription subscription = subscriptionService.update(id, leaderId, followerId);
        return SubscriptionDto.toDto(subscription);
    }

    @GetMapping("/leader/{leaderId}")
    List<SubscriptionDto> getByLeader(@PathVariable  int leaderId){
        User leader = userService.getById(leaderId);
        return subscriptionService.findByLeader(leader).stream().map(SubscriptionDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/follower/{followerId}")
    List<SubscriptionDto> getByFollower(@PathVariable  int followerId){
        User follower = userService.getById(followerId);
        return subscriptionService.findByFollower(follower).stream().map(SubscriptionDto::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int id){
        subscriptionService.deleteById(id);
    }
}

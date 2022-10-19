package com.backend.rest.dto;

import com.backend.domain.Subscription;
import com.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDto {
    private int id;
    private UserDto leader;
    private UserDto follower;

    public static SubscriptionDto toDto(Subscription subscription){
        return new SubscriptionDto(subscription.getId(),
                UserDto.toDto(subscription.getLeader()),
                UserDto.toDto(subscription.getFollower())
        );
    }

    public static Subscription toDomainObject(SubscriptionDto subscriptionDto, User leader, User follower){
        return new Subscription(subscriptionDto.getId(),
                leader,
                follower
        );
    }
}

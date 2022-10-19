package com.backend.services;

import com.backend.domain.*;
import com.backend.repository.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppDemoService implements AppDemoInterface{

    @NonNull private final UserService userService;
    private final UserRepository userRepository;
    @NonNull private final PostService postService;
    @NonNull private final RecipeService recipeService;
    @NonNull private final PostCommentService postCommentService;
    @NonNull private final SubscriptionService subscriptionService;
    @NonNull private final PictureService pictureService;

    @Override
    @Transactional
    public void userDemo() {

        for (User user: userService.getAll()) {

            System.out.println(user);
        }

        System.out.println("======================\n");
    }

    @Override
    public void postDemo() {

        for (Post post: postService.getAll()) {

            System.out.println(post);
        }

        System.out.println("======================\n");

    }

    @Override
    public void recipeDemo() {
        for (Recipe recipe: recipeService.getAll()) {

            System.out.println(recipe);
        }

        System.out.println("======================\n");

    }

    @Override
    public void commentDemo() {
        for (PostComment postComment : postCommentService.getAll()) {

            System.out.println(postComment);
        }

        System.out.println("======================\n");

    }

    @Override
    public void subscriptionDemo() {
        for (Subscription subscription: subscriptionService.getAll()) {

            System.out.println(subscription);
        }

        System.out.println("======================\n");
    }

    @Override
    public void pictureDemo() {
        for (Picture picture: pictureService.getAll()) {

            System.out.println(picture);
        }

        System.out.println("======================\n");
    }

    @Transactional
    public void appDemo(){
        userDemo();
        postDemo();
        recipeDemo();
        commentDemo();
        subscriptionDemo();
        pictureDemo();

    }
}

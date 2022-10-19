package com.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "recipe_comments")
public class RecipeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;
    @ManyToOne(targetEntity = Recipe.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe")
    private Recipe recipe;
    @Column(name = "text")
    private String text;
    @Column(name = "likes")
    private int likes;

}

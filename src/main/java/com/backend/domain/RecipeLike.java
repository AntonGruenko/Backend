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
@Table(name = "recipe_likes")
public class RecipeLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = Recipe.class)
    @JoinColumn(name = "recipe")
    private Recipe recipe;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "liker")
    private User liker;
}

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
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne(targetEntity = Recipe.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe")
    private Recipe recipe;
    @ManyToOne(targetEntity = Days.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "day")
    private Days day;
}

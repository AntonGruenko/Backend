package com.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.util.diff.Delta;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "author")
    private User author;
    @Column(name = "ingredients")
    private String ingredients;
    @Column(name = "guide")
    private String guide;
    @Column(name = "reccomendations")
    private String reccomendations;
    @Column(name = "time")
    private int time;
    @Column(name = "kcal")
    private int kcal;
    @Column(name = "proteins")
    private int proteins;
    @Column(name = "fats")
    private int fats;
    @Column(name = "carbohydrates")
    private int carbohydrates;
    @Column(name = "sugar")
    private int sugar;
    @Column(name = "likes")
    private int likes;
    @Column(name = "complexity")
    private int complexity;
    @Column(name = "tags")
    private String tags;
}

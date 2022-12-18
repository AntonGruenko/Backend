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
@Table(name = "days")
public class Days {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;
    @Column(name = "day")
    private int day;
    @Column(name = "kcal")
    private int kcal;
    @Column(name = "proteins")
    private int proteins;
    @Column(name = "fats")
    private int fats;
    @Column(name = "carbohydrates")
    private int carbohydrates;
    @Column(name = "isSuccessful")
    private boolean isSuccessful;
}
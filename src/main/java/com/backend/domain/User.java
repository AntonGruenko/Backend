package com.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;
    @Column(name = "profile_pic")
    private String profilePic;
    @Column(name = "kcal")
    private int kcal;
    @Column(name = "proteins")
    private int proteins;
    @Column(name = "fats")
    private int fats;
    @Column(name =  "carbohydrates")
    private int carbohydrates;


}

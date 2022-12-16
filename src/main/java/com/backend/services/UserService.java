package com.backend.services;

import com.backend.domain.User;

import java.util.List;

public interface UserService {

    User insert(User user);

    User update(int id, String name, String email, String password, String status, String profilePic, int kcal, int proteins, int fats, int carbohydrates);

    User getById(int id);

    List<User> findByName(String name);

    User getByEmail(String email);

    List<User> getAll();

    void deleteById(int id);


}

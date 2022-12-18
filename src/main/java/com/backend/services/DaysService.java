package com.backend.services;

import com.backend.domain.Days;
import com.backend.domain.Post;
import com.backend.domain.User;

import java.util.List;

public interface DaysService {
    Days insert(int userId, int day, int kcal, int proteins, int fats, int carbohydrates, boolean isSuccessful);

    Days update(int id, int userId, int day, int kcal, int proteins, int fats, int carbohydrates, boolean isSuccessful);

    Days getById(int id);

    List<Days> getByUser(User user);

    List<Days> getAll();

    void deleteById(int id);
}

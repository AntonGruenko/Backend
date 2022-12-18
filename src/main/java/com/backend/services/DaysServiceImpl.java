package com.backend.services;

import com.backend.domain.Days;
import com.backend.domain.Post;
import com.backend.domain.User;
import com.backend.repository.DaysRepository;
import com.backend.repository.UserRepository;
import lombok.NonNull;

import java.util.List;

public class DaysServiceImpl implements DaysService{
    @NonNull DaysRepository daysRepository;
    @NonNull UserRepository userRepository;

    @Override
    public Days insert(int userId, int day, int kcal, int proteins, int fats, int carbohydrates, boolean isSuccessful) {
        User user = (User) userRepository.findById(userId);
        Days days = Days.builder()
                .user(user)
                .day(day)
                .kcal(kcal)
                .proteins(proteins)
                .fats(fats)
                .carbohydrates(carbohydrates)
                .isSuccessful(isSuccessful)
                .build();
        return daysRepository.save(days);
    }

    @Override
    public Days update(int id, int userId, int day, int kcal, int proteins, int fats, int carbohydrates, boolean isSuccessful) {
        User user = (User) userRepository.findById(userId);
        Days days = Days.builder()
                .id(id)
                .user(user)
                .day(day)
                .kcal(kcal)
                .proteins(proteins)
                .fats(fats)
                .carbohydrates(carbohydrates)
                .isSuccessful(isSuccessful)
                .build();
        return daysRepository.save(days);
    }

    @Override
    public Days getById(int id) {
        return daysRepository.getById(id);
    }

    @Override
    public List<Days> getByUser(User user) {
        return daysRepository.findByUser(user);
    }

    @Override
    public List<Days> getAll() {
        return daysRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        daysRepository.deleteById(id);
    }
}

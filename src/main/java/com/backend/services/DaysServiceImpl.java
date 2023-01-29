package com.backend.services;

import com.backend.domain.Days;
import com.backend.domain.Post;
import com.backend.domain.User;
import com.backend.repository.DaysRepository;
import com.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DaysServiceImpl implements DaysService{
    @NonNull DaysRepository daysRepository;
    @NonNull UserRepository userRepository;

    @Transactional
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

    @Transactional
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
    public Days getByUserAndDay(int userId, int day) {
        User user = userRepository.getById(userId);
        return daysRepository.findByUserAndDay(user, day);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        daysRepository.deleteById(id);
    }
}

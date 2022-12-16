package com.backend.services;

import com.backend.domain.User;
import com.backend.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @NonNull UserRepository userRepository;

    @Override
    @Transactional
    public User insert(User user) {

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(int id, String name, String email, String password, String status, String profilePic, int kcal, int proteins, int fats, int carbohydrates) {
        User user = User.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .status(status)
                .profilePic(profilePic)
                .kcal(kcal)
                .proteins(proteins)
                .fats(fats)
                .carbohydrates(carbohydrates)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByNameContains(name);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}

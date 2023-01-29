package com.backend.services;

import com.backend.domain.Post;
import com.backend.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {

    Post insert(int authorId, String text, String picture);

    Post update(int id, int authorId, String text, String picture);

    Post getById(int id);

    List<Post> getByAuthor(User author);

    List<Post> findByText(String text);

    List<Post> getAll();

    void deleteById(int id);
}

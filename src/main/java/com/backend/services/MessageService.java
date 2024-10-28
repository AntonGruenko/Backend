package com.backend.services;

import com.backend.domain.Message;
import com.backend.domain.User;

import java.util.List;

public interface MessageService {

    Message insert(User sender, User receiver, String text, String picture);

    List<Message> getBySenderAndReceiver(User sender, User receiver);

    List<Message> getAllByUser(User user);
}

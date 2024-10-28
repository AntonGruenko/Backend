package com.backend.services;

import com.backend.domain.Message;
import com.backend.domain.User;
import com.backend.repository.MessageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{
    @NonNull MessageRepository messageRepository;

    @Transactional
    @Override
    public Message insert(User sender, User receiver, String text, String picture) {
        Message message = Message.builder().
                sender(sender).
                receiver(receiver).
                text(text)
                .picture(picture)
                .build();
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getBySenderAndReceiver(User sender, User receiver) {
        return messageRepository.findBySenderAndReceiver(sender, receiver);
    }

    @Override
    public List<Message> getAllByUser(User user) {
        return messageRepository.findAllBySenderOrReceiver(user, user);
    }
}

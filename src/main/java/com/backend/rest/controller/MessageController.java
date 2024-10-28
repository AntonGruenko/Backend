package com.backend.rest.controller;

import com.backend.domain.Message;
import com.backend.domain.User;
import com.backend.rest.dto.MessageDto;
import com.backend.services.MessageService;
import com.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    @GetMapping("/{senderId}/{receiverId}")
    List<MessageDto> getBySenderAndReceiver(
            @PathVariable int senderId,
            @PathVariable int receiverId
    ){
        User sender = userService.getById(senderId);
        User receiver = userService.getById(receiverId);
        return messageService.getBySenderAndReceiver(sender, receiver).stream().map(MessageDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    List<MessageDto> getByUserId(@PathVariable int userId){
        User user = userService.getById(userId);
        return messageService.getAllByUser(user).stream().map(MessageDto::toDto).collect(Collectors.toList());
    }

    @PostMapping()
    MessageDto insertMessage(
            @RequestParam int senderId,
            @RequestParam int receiverId,
            @RequestParam String text,
            @RequestParam String picture){
        User sender = userService.getById(senderId);
        User receiver = userService.getById(receiverId);
        return MessageDto.toDto(messageService.insert(sender, receiver, text, picture));
    }

    @MessageMapping("/send")
    @SendTo("/topic/public")
    MessageDto sendMessage(@Payload MessageDto messageDto){
        return messageDto;
    }
}

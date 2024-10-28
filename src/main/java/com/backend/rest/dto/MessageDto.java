package com.backend.rest.dto;

import com.backend.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private int id;
    private UserDto sender;
    private UserDto receiver;
    private String text;
    private String picture;

    public static MessageDto toDto(Message message){
        return new MessageDto(
                message.getId(),
                UserDto.toDto(message.getSender()),
                UserDto.toDto(message.getReceiver()),
                message.getText(),
                message.getPicture()
        );
    }
}

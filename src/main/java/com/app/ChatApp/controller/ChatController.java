package com.app.ChatApp.controller;


import com.app.ChatApp.entities.Message;
import com.app.ChatApp.entities.Room;
import com.app.ChatApp.payload.MessageRequest;
import com.app.ChatApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final RoomRepository roomRepository;


    //for sending and receiving messages

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest request) throws Exception {

        Room room = roomRepository.findByRoomId(request.getRoomId());

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        if(room!=null){
            room.getMessages().add(message);
            roomRepository.save(room);
        }else {
            throw new RuntimeException("Room not found!");
        }

        return message;
    }

}

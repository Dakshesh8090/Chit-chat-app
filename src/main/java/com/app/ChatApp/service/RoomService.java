package com.app.ChatApp.service;

import com.app.ChatApp.entities.Message;
import com.app.ChatApp.entities.Room;
import com.app.ChatApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Object createRoom(String roomId){

        //room is already there
        if(roomRepository.findByRoomId(roomId) != null){
            throw new IllegalArgumentException("Room is Already created!");
        }

        Room room = new Room();
        room.setRoomId(roomId);
        Room savedRoom = roomRepository.save(room);

        return savedRoom;
    }

    public Room getRoomById(String roomId){
        Room room = roomRepository.findByRoomId(roomId);


        if(room == null){
            throw new IllegalArgumentException("You can not join the room! Because room does not exist's");
        }

        //otherwise you can join the room
        return room;
    }

   public List<Message> getMessagesWithPagination(String roomId, int page, int size){

        Room room = roomRepository.findByRoomId(roomId);

        if(room == null){
            throw new IllegalArgumentException("Room not found with : " + roomId);
        }

       List<Message> messages = room.getMessages();
        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        List<Message> paginatedMessages = messages.subList(start, end);
         return paginatedMessages;

   }

}

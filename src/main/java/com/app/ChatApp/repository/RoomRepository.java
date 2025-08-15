package com.app.ChatApp.repository;

import com.app.ChatApp.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {

    //get room using roomId
    public Room findByRoomId(String id);
}

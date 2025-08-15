package com.app.ChatApp.controller;


import com.app.ChatApp.entities.Message;
import com.app.ChatApp.service.RoomService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {


    private final RoomService roomService;

    //create room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId){
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(roomId));
    }

    //get room : join room krne ki koshish kr rha hai
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId){
       return ResponseEntity.status(HttpStatus.OK).body(roomService.getRoomById(roomId));
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId,
                                                     @RequestParam(value="page",defaultValue = "0", required = false) int page,
                                                     @RequestParam(value = "size", defaultValue = "20", required = false) int size ){

        return ResponseEntity.status(HttpStatus.OK).body(roomService.getMessagesWithPagination(roomId, page, size));
    }

}

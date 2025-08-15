package com.app.ChatApp.payload;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRequest {

    private String content;

    private String sender;

    private String roomId;


}

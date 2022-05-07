package com.example.demo.src.chatrooms.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetChatroomsRes {
    private int chatroomId;
    private int postId;
    private String createdAt;
    private String nickname;
    private String name;
    private String URL;
}

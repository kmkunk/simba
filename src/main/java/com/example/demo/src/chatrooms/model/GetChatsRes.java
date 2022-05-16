package com.example.demo.src.chatrooms.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetChatsRes {
    private int userId;
    private String nickname;
    private String content;
    private String createdAt;
}

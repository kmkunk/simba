package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCommentsRes {
    private int commentId;
    private int villageLifePostId;
    private String content;
    private String createdAt;
}

package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetReviewsRes {
    private int postId;
    private int buyerId;
    private String content;
    private String createdAt;
    private String nickname;
    private String name;
}

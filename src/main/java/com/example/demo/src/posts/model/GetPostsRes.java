package com.example.demo.src.posts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPostsRes {
    private int postId;
    private String title;
    private int price;
    private String createdAt;
    private String name;
    private int chatroomCount;
    private int interestPostCount;
    private String URL;
}

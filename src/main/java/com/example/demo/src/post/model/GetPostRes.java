package com.example.demo.src.post.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPostRes {
    private int postId;
    private int userId;
    private int postCategoryId;
    private int village1Id;
    private String title;
    private String content;
    private int price;
    private String createdAt;
    private String updatedAt;
    private String status;
}

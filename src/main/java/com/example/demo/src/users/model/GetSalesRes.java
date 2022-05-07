package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSalesRes {
    private int postId;
    private String title;
    private int price;
    private String createdAt;
    private String updatedAt;
    private String status;
    private int chatroomCount;
    private int interestPostCount;
    private String URL;
    private String name;
}

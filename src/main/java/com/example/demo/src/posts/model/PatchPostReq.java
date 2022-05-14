package com.example.demo.src.posts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatchPostReq {
    private int userId;
    private int postCategoryId;
    private String title;
    private String content;
    private int price;
}

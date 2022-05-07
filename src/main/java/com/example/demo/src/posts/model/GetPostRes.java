package com.example.demo.src.posts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPostRes {
    private int postId;
    private String title;
    private int price;
    private String content;
    private String createdAt;
    private String nickname;
    private String vname;
    private String ename;
    private String URL;
}

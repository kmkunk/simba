package com.example.demo.src.posts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPostURLsRes {
    private int postImageId;
    private String URL;
    private boolean representative;
}

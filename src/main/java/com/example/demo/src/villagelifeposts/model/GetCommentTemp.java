package com.example.demo.src.villagelifeposts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCommentTemp {
    private int commentId;
    private int villageLifePostId;
    private String nickname;
    private String content;
    private String createdAt;
}

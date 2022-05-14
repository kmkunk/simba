package com.example.demo.src.villagelifeposts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetVillageLifePostInfoRes {
    private int villageLifePostId;
    private String content;
    private String createdAt;
    private String nickname;
    private String vname;
    private String cname;
    private int villageLifePostLikeCount;
    private int commentCount;
}

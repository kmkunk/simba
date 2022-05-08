package com.example.demo.src.villagelifeposts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetVillageLifePostsRes {
    private int villageLifePostId;
    private String title;
    private String createdAt;
    private String cname;
    private String vname;
    private String nickname;
    private int commentCount;
    private int villageLifePostLikeCount;
    private String URL;
}

package com.example.demo.src.villagelifeposts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetVillageLifePostLikesRes {
    private int userId;
    private String nickname;
    private String vname;
}

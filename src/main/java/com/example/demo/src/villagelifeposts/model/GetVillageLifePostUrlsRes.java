package com.example.demo.src.villagelifeposts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetVillageLifePostUrlsRes {
    private int villageLifePostImageId;
    private String URL;
    private boolean representative;
}

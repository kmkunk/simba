package com.example.demo.src.villagelifeposts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatchVillageLifePostReq {
    private int userId;
    private int villageLifePostCategoryId;
    private String title;
    private String content;
}

package com.example.demo.src.villagelifeposts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetPostAndComments {
    private GetVillageLifePostRes getVillageLifePostRes;
    private List<GetCommentTemp> getCommentTemps;
}

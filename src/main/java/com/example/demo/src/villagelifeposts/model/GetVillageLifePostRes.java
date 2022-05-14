package com.example.demo.src.villagelifeposts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetVillageLifePostRes {
    private GetVillageLifePostInfoRes getVillageLifePostInfoRes;
    private List<GetCommentsRes> getCommentsResList;
    private List<GetVillageLifePostUrlsRes> getVillageLifePostUrlsResList;
}

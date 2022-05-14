package com.example.demo.src.posts.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPostRes {
    private GetPostInfoRes getPostInfoRes;
    private List<GetPostUrlsRes> getPostUrlsResList;
}

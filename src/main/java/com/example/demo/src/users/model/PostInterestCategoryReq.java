package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.security.DenyAll;

@Data
@AllArgsConstructor
public class PostInterestCategoryReq {
    private int postCategoryId;
}

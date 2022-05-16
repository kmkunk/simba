package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetInterestCategoryRes {
    private int postCategoryId;
    private String name;
}

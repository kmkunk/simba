package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetMannersRes {
    private int userId;
    private String name;
}

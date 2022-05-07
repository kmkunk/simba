package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserRes {
    private int userId;
    private String nickname;
    private String URL;
    private String vname;
}

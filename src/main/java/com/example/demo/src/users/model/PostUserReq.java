package com.example.demo.src.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostUserReq {
    private String nickname;
    private String phone;
    private String email;
    private String URL;
}

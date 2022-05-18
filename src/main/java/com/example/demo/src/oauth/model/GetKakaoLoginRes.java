package com.example.demo.src.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetKakaoLoginRes {
    private String accessToken;
    private String refreshToken;
}

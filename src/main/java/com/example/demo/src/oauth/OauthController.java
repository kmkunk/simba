package com.example.demo.src.oauth;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.oauth.model.GetKakaoLoginRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OauthController {
    private final OauthService oauthService;

    @GetMapping("/kakao")
    public BaseResponse<GetKakaoLoginRes> kakaoCallback(@RequestParam String code) {
        try {
            GetKakaoLoginRes kakaoToken = oauthService.getKakaoToken(code);
            return new BaseResponse<>(kakaoToken);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}

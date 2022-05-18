package com.example.demo.src.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class KakaoLoginController {
    @GetMapping("/kakao")
    public String getKakao() {
        return "redirect:https://kauth.kakao.com/oauth/authorize?client_id=6883a185589df78f80330dda77f725d3&redirect_uri=http://localhost:9000/oauth/kakao&response_type=code";
    }
}

package com.example.demo.src.post;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.post.model.GetPostRes;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PostProvider postProvider;

    /**
     * 특정 지역에 대한 중고거래 게시글 리스트
     * [GET] /posts/:villageName
     * @return BaseResponse<GetPostRes>
     * */
    @GetMapping("/{villageName}")
    public BaseResponse<List<GetPostRes>> getPosts(@PathVariable("villageName") String villageName) {
        try {
            //프로바이더에서 getPosts 만들기
            List<GetPostRes> getPostRes = postProvider.getPosts(villageName);
            return new BaseResponse<>(getPostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}

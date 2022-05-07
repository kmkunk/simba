package com.example.demo.src.post;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.post.model.GetPostRes;
import com.example.demo.src.post.model.GetPostsRes;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostProvider postProvider;

    /**
     * 특정 지역에 대한 중고거래 게시글 리스트
     * [GET] /posts/:village
     * @return BaseResponse<List<GetPostsRes>>
     */
    @GetMapping("/{village}")
    public BaseResponse<List<GetPostsRes>> getPosts(@PathVariable("village") String village) {
        try {
            List<GetPostsRes> getPostsRes = postProvider.getPosts(village);
            return new BaseResponse<>(getPostsRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 중고거래 게시글 조회 API
     * [GET] /posts/:village/:postId
     * @return BaseResponse<GetPostRes>
     */
    @GetMapping("/{village}/{postId}")
    public BaseResponse<GetPostRes> getPost(@PathVariable("village") String village, @PathVariable("postId") int postId) {
        try {
            GetPostRes getPostRes = postProvider.getPost(village, postId);
            return new BaseResponse<>(getPostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}

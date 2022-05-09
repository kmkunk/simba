package com.example.demo.src.posts;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.posts.model.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostProvider postProvider;
    private final PostService postService;

    /**
     * 특정 지역에 대한 중고거래 게시글 리스트 API
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

    /**
     * 중고거래 게시글 생성 API
     * [POST] /posts/:village
     * @return BaseResponse<PostPostRes>
     */
    @PostMapping("/{village}")
    public BaseResponse<PostPostRes> postPost(@PathVariable("village") String village,
                                              @RequestBody PostPostReq postPostReq) {
        try {
            PostPostRes postPostRes = postService.postPost(village, postPostReq);
            return new BaseResponse<>(postPostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 중고거래 게시글 수정 API
     * [PATCH] /posts/:village/:postId
     * @return BaseResponse<Integer>
     */
    @PatchMapping("/{village}/{postId}")
    public BaseResponse<Integer> patchPost(@PathVariable("village") String village,
                                                @PathVariable("postId") int postId,
                                                @RequestBody PatchPostReq patchPostReq) {
        try {
            Integer patchPostRes = postService.patchPost(village, postId, patchPostReq);
            return new BaseResponse<>(patchPostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}

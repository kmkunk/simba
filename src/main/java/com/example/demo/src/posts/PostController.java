package com.example.demo.src.posts;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.posts.model.*;
import com.example.demo.utils.JwtService;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostProvider postProvider;
    private final PostService postService;
    private final JwtService jwtService;

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
            if(postId<=0) { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }
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
    public BaseResponse<Integer> postPost(@PathVariable("village") String village,
                                              @RequestBody PostPostReq postPostReq) {
        int userId = postPostReq.getUserId();
        int postCategoryId = postPostReq.getPostCategoryId();
        String title = postPostReq.getTitle();
        String content = postPostReq.getContent();
        int price = postPostReq.getPrice();

        if(userId<=0 || postCategoryId<=0 || title==null || content==null || price<0)
        { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }


        try {
            int userIdByJwt = jwtService.getUserIdx();
            if(userId != userIdByJwt) { return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT); }

            Integer postPostRes = postService.postPost(village, postPostReq);
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
        int userId = patchPostReq.getUserId();

        try {
            int userIdByJwt = jwtService.getUserIdx();
            if(userId != userIdByJwt) { return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT); }

            Integer patchPostRes = postService.patchPost(village, postId, patchPostReq);
            return new BaseResponse<>(patchPostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 중고거래 게시글 삭제 API
     * [DELETE] /posts/:village/:postId
     * @return BaseResponse<Integer>
     */
    @DeleteMapping("/{village}/{postId}")
    public BaseResponse<Integer> deletePost(@PathVariable("village") String village,
                                            @PathVariable("postId") int postId,
                                            @RequestBody DeletePostReq deletePostReq) {
        int userId = deletePostReq.getUserId();

        try {
            int userIdByJwt = jwtService.getUserIdx();
            if(userId != userIdByJwt) { return new BaseResponse<>(BaseResponseStatus.INVALID_USER_JWT); }

            Integer deletePostRes = postService.deletePost(village, postId, deletePostReq);
            return new BaseResponse<>(deletePostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 카테고리 별 게시글 리스트 API
     * [GET] /posts/:village/:postcategoryId
     * @return BaseResponse<List<GetPostsRes>>
     */
    @GetMapping("/{village}/category/{postcategoryId}")
    public BaseResponse<List<GetPostsRes>> getPostsByCategoryId(@PathVariable("village") String village,
                                                                @PathVariable("postcategoryId") int postcategoryId) {
        try {
            List<GetPostsRes> getPostsByCategoryIdResList = postProvider.getPostsByCategoryId(village, postcategoryId);
            return new BaseResponse<>(getPostsByCategoryIdResList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}

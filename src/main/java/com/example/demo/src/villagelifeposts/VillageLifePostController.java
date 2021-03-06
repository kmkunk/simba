package com.example.demo.src.villagelifeposts;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.villagelifeposts.model.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/villagelifeposts")
public class VillageLifePostController {
    private final VillageLifePostProvider villageLifePostProvider;
    private final VillageLifePostService villageLifePostService;

    /**
     * 특정 지역에 대한 동네 생활 게시글 리스트 API
     * [GET] /villagelifeposts/:village
     * @return BaseResponse<List<GetVillageLifePostsRes>>
     */
    @GetMapping("/{village}")
    public BaseResponse<List<GetVillageLifePostsRes>> getVillageLifePosts(@PathVariable("village") String village) {
        try {
            List<GetVillageLifePostsRes> getVillageLifePostsRes = villageLifePostProvider.getVillageLifePosts(village);
            return new BaseResponse<>(getVillageLifePostsRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 동네 생활 게시글 조회 API
     * [GET] /villagelifeposts/:village/:postId
     * @return BaseResponse<GetVillageLifePostRes>
     */
    @GetMapping("/{village}/{postId}")
    public BaseResponse<GetVillageLifePostRes> getVillageLifePost(@PathVariable("village") String village,
                                                                  @PathVariable("postId") int postId) {
        try {
            if(postId<=0) { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }
            GetVillageLifePostRes villageLifePost = villageLifePostProvider.getVillageLifePost(village, postId);
            return new BaseResponse<>(villageLifePost);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 동네 생활 게시글 생성 API
     * [POST] /villagelifeposts/:village
     * @return BaseResponse<PostVillageLifePostRes>
     */
    @PostMapping("/{village}")
    public BaseResponse<Integer> postVillageLifePost(@PathVariable("village") String village,
                                                        @RequestBody PostVillageLifePostReq postVillageLifePostReq) {
        try {
            Integer postVillageLifePostRes =
                    villageLifePostService.postVillageLifePost(village, postVillageLifePostReq);
            return new BaseResponse<>(postVillageLifePostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 동네 생활 게시글 수정 API
     * [PATCH] /villagelifeposts/:village/:postId
     * @return BaseResponse<Integer>
     */
    @PatchMapping("/{village}/{postId}")
    public BaseResponse<Integer> patchVillageLifePost(@PathVariable("village") String village,
                                                      @PathVariable("postId") int postId,
                                                      @RequestBody PatchVillageLifePostReq patchVillageLifePostReq) {
        try {
            Integer patchVillageLifePostRes =
                    villageLifePostService.patchVillageLifePost(village, postId, patchVillageLifePostReq);
            return new BaseResponse<>(patchVillageLifePostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 동네 생활 게시글 삭제 API
     * [DELETE] /villagelifeposts/:village/:postId
     * @return BaseResponse<Integer>
     */
    @DeleteMapping("/{village}/{postId}")
    public BaseResponse<Integer> deleteVillageLifePost(@PathVariable("village") String village,
                                                      @PathVariable("postId") int postId,
                                                      @RequestBody DeleteVillageLifePostReq deleteVillageLifePostReq) {
        try {
            Integer deleteVillageLifePostRes =
                    villageLifePostService.deleteVillageLifePost(village, postId, deleteVillageLifePostReq);
            return new BaseResponse<>(deleteVillageLifePostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 동네 생활 게시글 공감 조회 API
     * [GET] /villagelifeposts/:village/:postId/likes
     * @return BaseResponse<List<GetVillageLifePostLikeRes>>
     */
    @GetMapping("/{village}/{postId}/likes")
    public BaseResponse<List<GetVillageLifePostLikesRes>> getVillageLifePostLikes(@PathVariable("village") String village,
                                                                  @PathVariable("postId") int postId) {
        try {
            if(postId<=0) { return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR); }
            List<GetVillageLifePostLikesRes> getVillageLifePostLikesResList =
                    villageLifePostProvider.getVillageLifePostLikes(village, postId);
            return new BaseResponse<>(getVillageLifePostLikesResList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}

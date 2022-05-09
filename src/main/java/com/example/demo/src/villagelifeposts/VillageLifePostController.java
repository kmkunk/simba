package com.example.demo.src.villagelifeposts;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.villagelifeposts.model.GetVillageLifePostRes;
import com.example.demo.src.villagelifeposts.model.GetVillageLifePostsRes;
import com.example.demo.src.villagelifeposts.model.PostVillageLifePostReq;
import com.example.demo.src.villagelifeposts.model.PostVillageLifePostRes;

import java.util.List;

import lombok.RequiredArgsConstructor;

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
            GetVillageLifePostRes getVillageLifePostRes = villageLifePostProvider.getVillageLifePost(village, postId);
            return new BaseResponse<>(getVillageLifePostRes);
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
    public BaseResponse<PostVillageLifePostRes> postVillageLifePost(@PathVariable("village") String village,
                                                        @RequestBody PostVillageLifePostReq postVillageLifePostReq) {
        try {
            PostVillageLifePostRes postVillageLifePostRes =
                    villageLifePostService.postVillageLifePost(village, postVillageLifePostReq);
            return new BaseResponse<>(postVillageLifePostRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}

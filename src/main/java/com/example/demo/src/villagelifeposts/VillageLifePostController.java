package com.example.demo.src.villagelifeposts;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.villagelifeposts.model.GetVillageLifePostRes;
import com.example.demo.src.villagelifeposts.model.GetVillageLifePostsRes;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/villagelifeposts")
public class VillageLifePostController {
    private final VillageLifePostProvider villageLifePostProvider;

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
}

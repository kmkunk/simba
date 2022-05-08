package com.example.demo.src.villagelifeposts;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.villagelifeposts.model.GetVillageLifePostRes;
import com.example.demo.src.villagelifeposts.model.GetVillageLifePostsRes;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VillageLifePostProvider {
    private final VillageLifePostDao villageLifePostDao;

    public List<GetVillageLifePostsRes> getVillageLifePosts(String village) throws BaseException {
        try {
            List<GetVillageLifePostsRes> getVillageLifePostsRes = villageLifePostDao.getVillageLifePosts(village);
            return getVillageLifePostsRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public GetVillageLifePostRes getVillageLifePost(String village, int postId) throws BaseException {
        try {
            GetVillageLifePostRes getVillageLifePostRes = villageLifePostDao.getVillageLifePost(village, postId);
            return getVillageLifePostRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

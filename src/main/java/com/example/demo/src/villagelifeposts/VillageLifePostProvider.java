package com.example.demo.src.villagelifeposts;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.villagelifeposts.model.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VillageLifePostProvider {
    private final VillageLifePostDao villageLifePostDao;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public List<GetVillageLifePostsRes> getVillageLifePosts(String village) throws BaseException {
        try {
            List<GetVillageLifePostsRes> getVillageLifePostsRes = villageLifePostDao.getVillageLifePosts(village);
            return getVillageLifePostsRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public GetVillageLifePostRes getVillageLifePost(String village, int postId) throws BaseException {
        try {
            GetVillageLifePostInfoRes getVillageLifePostInfoRes =
                    villageLifePostDao.getVillageLifePost(village, postId);
            List<GetCommentsRes> getCommentsResList = villageLifePostDao.getComments(postId);
            List<GetVillageLifePostUrlsRes> getVillageLifePostUrlsResList =
                    villageLifePostDao.getVillageLifePostUrls(postId);
            GetVillageLifePostRes getVillageLifePostRes =
                    new GetVillageLifePostRes(getVillageLifePostInfoRes, getCommentsResList,
                            getVillageLifePostUrlsResList);
            return getVillageLifePostRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

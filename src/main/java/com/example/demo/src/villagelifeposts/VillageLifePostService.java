package com.example.demo.src.villagelifeposts;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.villagelifeposts.model.DeleteVillageLifePostReq;
import com.example.demo.src.villagelifeposts.model.PatchVillageLifePostReq;
import com.example.demo.src.villagelifeposts.model.PostVillageLifePostReq;
import com.example.demo.src.villagelifeposts.model.PostVillageLifePostRes;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VillageLifePostService {
    private final VillageLifePostDao villageLifePostDao;

    public Integer postVillageLifePost(String village, PostVillageLifePostReq postVillageLifePostReq)
        throws BaseException {
        try {
            Integer postVillageLifePostRes =
                    villageLifePostDao.postVillageLifePost(village, postVillageLifePostReq);
            return postVillageLifePostRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public Integer patchVillageLifePost(String village, int postId, PatchVillageLifePostReq patchVillageLifePostReq)
        throws BaseException {
        try {
            Integer patchVillageLifePostRes =
                    villageLifePostDao.patchVillageLifePost(village, postId, patchVillageLifePostReq);
            return patchVillageLifePostRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public Integer deleteVillageLifePost(String village, int postId, DeleteVillageLifePostReq deleteVillageLifePostReq)
            throws BaseException {
        try {
            Integer deleteVillageLifePostRes =
                    villageLifePostDao.deleteVillageLifePost(village, postId, deleteVillageLifePostReq);
            return deleteVillageLifePostRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

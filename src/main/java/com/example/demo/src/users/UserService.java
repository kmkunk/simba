package com.example.demo.src.users;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.posts.model.PatchPostReq;
import com.example.demo.src.users.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final JwtService jwtService;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public PostUserRes postUser(PostUserReq postUserReq) throws BaseException {
        try {
            PostUserRes postUserRes = userDao.postUser(postUserReq);
            return postUserRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public PostLoginRes postLogin(PostLoginReq postLoginReq) throws BaseException {
        GetUserPhoneRes getUserPhoneRes = userDao.getUserPhone(postLoginReq.getUserId());
        String encryptPhone1;
        String encryptPhone2;
        try {
            encryptPhone1 = new SHA256().encrypt(postLoginReq.getPhone());
            encryptPhone2 = new SHA256().encrypt(getUserPhoneRes.getPhone());
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.PASSWORD_DECRYPTION_ERROR);
        }

        if(encryptPhone2.equals(encryptPhone1)) {
            int userId = getUserPhoneRes.getUserId();
            String jwt = jwtService.createJwt(userId);
            return new PostLoginRes(userId, jwt);
        } else {
            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Integer patchUser(int userId, PatchUserReq patchUserReq) throws BaseException {
        try {
            Integer patchUserRes = userDao.patchUser(userId, patchUserReq);
            return patchUserRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Integer deleteReview(int userId, int postId) throws BaseException {
        try {
            Integer deleteReviewRes = userDao.deleteReview(userId, postId);
            return deleteReviewRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Integer postInterestCategory(int userId, PostInterestCategoryReq postInterestCategoryReq)
            throws BaseException {
        try {
            Integer postInterestCategoryRes = userDao.postInterestCategory(userId, postInterestCategoryReq);
            return postInterestCategoryRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Integer deleteInterestCategory(int userId, int interestcategoryId) throws BaseException {
        try {
            Integer deleteInterestCategoryRes = userDao.deleteInterestCategory(userId, interestcategoryId);
            return deleteInterestCategoryRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Integer postKeyword(int userId, PostKeywordReq postKeywordReq) throws BaseException {
        try {
            Integer postKeywordRes = userDao.postKeyword(userId, postKeywordReq);
            return postKeywordRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Integer deleteKeyword(int userId, int keywordId) throws BaseException {
        try {
            Integer deleteKeywordRes = userDao.deleteKeyword(userId, keywordId);
            return deleteKeywordRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

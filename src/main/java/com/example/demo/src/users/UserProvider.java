package com.example.demo.src.users;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.users.model.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProvider {
    private final UserDao userDao;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public GetUserRes getUser(int userId) throws BaseException {
        try {
            GetUserRes getUserRes = userDao.getUser(userId);
            return getUserRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public List<GetBadgesRes> getBadges(int userId) throws BaseException {
        try {
            List<GetBadgesRes> getBadgesRes = userDao.getBadges(userId);
            return getBadgesRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public List<GetSalesRes> getSales(int userId, String status) throws BaseException {
        try {
            List<GetSalesRes> getSalesRes = userDao.getSales(userId, status);
            return getSalesRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public List<GetReviewsRes> getReviews(int userId) throws BaseException {
        try {
            List<GetReviewsRes> getReviewsRes = userDao.getReviews(userId);
            return getReviewsRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public List<GetMannersRes> getManners(int userId) throws BaseException {
        try {
            List<GetMannersRes> getMannersRes = userDao.getManners(userId);
            return getMannersRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public List<GetBadMannersRes> getBadManners(int userId) throws BaseException {
        try {
            List<GetBadMannersRes> getBadMannersRes = userDao.getBadManners(userId);
            return getBadMannersRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public GetEstimationsRes getEstimations(int userId) throws BaseException {
        try {
            List<GetMannersRes> getMannersRes = userDao.getManners(userId);
            List<GetBadMannersRes> getBadMannersRes = userDao.getBadManners(userId);
            GetEstimationsRes getEstimationsRes = new GetEstimationsRes(getMannersRes, getBadMannersRes);
            return getEstimationsRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

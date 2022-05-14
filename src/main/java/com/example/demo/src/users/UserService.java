package com.example.demo.src.users;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
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
}

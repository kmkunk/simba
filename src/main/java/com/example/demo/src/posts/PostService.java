package com.example.demo.src.posts;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.posts.model.PatchPostReq;
import com.example.demo.src.posts.model.PostPostReq;
import com.example.demo.src.posts.model.PostPostRes;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostDao postDao;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public PostPostRes postPost(String village, PostPostReq postPostReq) throws BaseException {
        try {
            PostPostRes postPostRes = postDao.postPost(village, postPostReq);
            return postPostRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public Integer patchPost(String village, int postId, PatchPostReq patchPostReq) throws BaseException {
        try {
            Integer patchPostRes = postDao.patchPost(village, postId, patchPostReq);
            return patchPostRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

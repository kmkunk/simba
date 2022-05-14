package com.example.demo.src.posts;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.posts.model.GetPostInfoRes;
import com.example.demo.src.posts.model.GetPostRes;
import com.example.demo.src.posts.model.GetPostURLsRes;
import com.example.demo.src.posts.model.GetPostsRes;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostProvider {
    private final PostDao postDao;

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public List<GetPostsRes> getPosts(String village) throws BaseException {
        try {
            List<GetPostsRes> getPostsRes = postDao.getPosts(village);
            return getPostsRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    public GetPostRes getPost(String village, int postId) throws BaseException {
        try {
            GetPostInfoRes getPostInfoRes = postDao.getPostInfo(village, postId);
            List<GetPostURLsRes> getPostURLsResList = postDao.getPostURLs(postId);
            GetPostRes getPostRes = new GetPostRes(getPostInfoRes, getPostURLsResList);
            return getPostRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

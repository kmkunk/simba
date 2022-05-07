package com.example.demo.src.post;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.post.model.GetPostRes;
import com.example.demo.src.post.model.GetPostsRes;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostProvider {
    private final PostDao postDao;

    public List<GetPostsRes> getPosts(String village) throws BaseException {
        try {
            List<GetPostsRes> getPostsRes = postDao.getPosts(village);
            return getPostsRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public GetPostRes getPost(String village, int postId) throws BaseException {
        try {
            GetPostRes getPostRes = postDao.getPost(village, postId);
            return getPostRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}

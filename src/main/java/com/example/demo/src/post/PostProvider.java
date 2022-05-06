package com.example.demo.src.post;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.post.model.GetPostRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostProvider {
    private final PostDao postDao;

    public List<GetPostRes> getPosts(String villageName) throws BaseException {
        try {
            List<GetPostRes> getPostRes = postDao.getPosts(villageName);
            return getPostRes;
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }
}


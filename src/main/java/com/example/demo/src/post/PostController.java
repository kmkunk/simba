package com.example.demo.src.post;

import com.example.demo.src.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final PostProvider postProvider;
    //임시로 서비스 레이어 거치지 말고 바로 디비로 접근
    private final PostDao postDao;

    @GetMapping("/{villageName}")
    public List<Post> getPosts(@PathVariable("villageName") String villageName) {
        //예외처리할것! 일단 임시 테스트하는거임
        return postDao.getPosts();
    }
}

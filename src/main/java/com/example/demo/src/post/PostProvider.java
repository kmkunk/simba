package com.example.demo.src.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostProvider {
    private final PostDao postDao;


}


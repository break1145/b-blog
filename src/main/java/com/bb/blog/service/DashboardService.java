package com.bb.blog.service;

import com.bb.blog.repository.PostRepository;
import com.bb.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public Map<String, Integer> getDashboardInfo(){
        Map<String, Integer> map = new HashMap<>();
        map.put("article-count", postRepository.findAll().size());
        map.put("user-count", userRepository.findAll().size());
        return map;

    }
}

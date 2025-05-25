package com.bb.blog.service;

import com.bb.blog.entity.Post;
import com.bb.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Integer id, Post post) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("文章不存在"));
        
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        
        return postRepository.save(existingPost);
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    public Post getPostById(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("文章不存在"));
    }

    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> getPostsByUserId(Integer userId, Pageable pageable) {
        return postRepository.findByUserId(userId, pageable);
    }

    public Page<Post> searchPosts(String keyword, Pageable pageable) {
        return postRepository.findByTitleContaining(keyword, pageable);
    }
}
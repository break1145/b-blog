package com.bb.blog.service;

import com.bb.blog.entity.dto.PostDto;
import com.bb.blog.entity.Post;
import com.bb.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public Page<PostDto> getAllPostsWithDto(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(post -> {
            // 在事务中初始化懒加载的user对象
            post.getUser().getUsername(); // 触发懒加载
            return PostDto.fromEntity(post);
        });
    }

    @Transactional(readOnly = true)
    public Page<PostDto> getPostsByUserIdWithDto(Integer userId, Pageable pageable) {
        Page<Post> posts = postRepository.findByUserId(userId, pageable);
        return posts.map(post -> {
            post.getUser().getUsername(); // 触发懒加载
            return PostDto.fromEntity(post);
        });
    }

    @Transactional(readOnly = true)
    public Page<PostDto> searchPostsWithDto(String keyword, Pageable pageable) {
        Page<Post> posts = postRepository.findByTitleContaining(keyword, pageable);
        return posts.map(post -> {
            post.getUser().getUsername(); // 触发懒加载
            return PostDto.fromEntity(post);
        });
    }

    @Transactional(readOnly = true)
    public PostDto getPostByIdWithDto(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("文章不存在"));
        post.getUser().getUsername(); // 触发懒加载
        return PostDto.fromEntity(post);
    }
}
package com.bb.blog.controller;

import com.bb.blog.entity.Post;
import com.bb.blog.entity.response.ApiResponse;
import com.bb.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(postService.createPost(post))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePost(@PathVariable Integer id, @RequestBody Post post) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(postService.updatePost(id, post))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(null)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPostById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(postService.getPostById(id))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPosts(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(postService.getAllPostsWithDto(pageable))
                .build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getPostsByUserId(
            @PathVariable Integer userId,
            Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(postService.getPostsByUserIdWithDto(userId, pageable))
                .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchPosts(
            @RequestParam String keyword,
            Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(postService.searchPostsWithDto(keyword, pageable))
                .build());
    }
}
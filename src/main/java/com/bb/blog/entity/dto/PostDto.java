package com.bb.blog.entity.dto;

import com.bb.blog.entity.Post;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PostDto {
    private Integer id;
    private UserDto user;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostDto fromEntity(Post post) {
        if (post == null) return null;
        
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setUser(UserDto.fromEntity(post.getUser()));
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto;
    }
}
package com.bb.blog.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.bb.blog.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(Integer userId);
    Page<Post> findByUserId(Integer userId, Pageable pageable);
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}
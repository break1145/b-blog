package com.bb.blog.controller;

import com.bb.blog.entity.User;
import com.bb.blog.entity.response.ApiResponse;
import com.bb.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(userService.createUser(user))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(userService.updateUser(id, user))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(null)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(userService.getUserById(id))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(userService.getAllUsers())
                .build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(userService.findByUsername(username))
                .build());
    }
}
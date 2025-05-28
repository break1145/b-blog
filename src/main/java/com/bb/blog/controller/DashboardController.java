package com.bb.blog.controller;

import com.bb.blog.entity.response.ApiResponse;
import com.bb.blog.service.DashboardService;
import com.bb.blog.service.PostService;
import com.bb.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/api/admin/dashboard")
    public ResponseEntity<ApiResponse> getDashboardData() {
        return ResponseEntity.ok(ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(dashboardService.getDashboardInfo())
                .build());
    }
}

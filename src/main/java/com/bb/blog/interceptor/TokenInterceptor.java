package com.bb.blog.interceptor;

import com.bb.blog.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil; // 注入修改后的 JwtUtil

    // 用于存储用户主题 的请求属性名
    public static final String REQUEST_ATTR_CURRENT_EMAIL = "currentSubject";
    // 用于返回新 Token 的响应头名
    public static final String RESPONSE_HEADER_NEW_TOKEN = "X-New-Token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从请求头获取 Token
        String token = jwtUtil.resolveToken(request);
        System.out.println(token);
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            System.out.println("Received OPTIONS request, bypassing token interceptor.");
            return true;
        }

        // 如果 Token 不存在，返回 401 Unauthorized
        if (token == null) {
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "缺少 Authorization 请求头或格式错误");
            return false;
        }

        // 2. 校验 Token 签名和基本结构 (不考虑是否过期，ExpiredJwtException 会被 isTokenValid 捕获)
        if (!jwtUtil.isTokenValid(token)) {
             sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "无效的 Token 签名或结构");
             return false;
        }

        // 3. 检查 Token 是否已过期
        if (jwtUtil.isTokenExpired(token)) {
             sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token 已过期");
             return false;
        }

        // 4. Token 有效，解析用户主题
        String subject = jwtUtil.extractSubject(token);
        if (subject == null || subject.isEmpty()) {
             sendError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token 有效但缺少主题");
             return false;
        }

        // 将用户主题存储在请求属性中，方便后续 Controller 访问
        request.setAttribute(REQUEST_ATTR_CURRENT_EMAIL, subject);

        // 5. 检查 Token 是否接近过期，如果是，生成新 Token 并添加到响应头
        if (jwtUtil.isTokenAboutToExpire(token)) {
            String newToken = jwtUtil.generateToken(subject); // 根据旧 Token 的主题生成新 Token
            response.setHeader(RESPONSE_HEADER_NEW_TOKEN, newToken);
            // 注意：这里只是添加了头，请求会继续处理，客户端需要读取这个头并更新 Token
            System.out.println("token已刷新。newToken = " + newToken);
        }

        return true;
    }


    private void sendError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"error\":\"" + message + "\"}");
        response.getWriter().flush();
    }
}
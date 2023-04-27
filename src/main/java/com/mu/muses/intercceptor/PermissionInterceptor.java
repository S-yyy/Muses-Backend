package com.mu.muses.intercceptor;

import com.mu.muses.service.TokenService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

public class PermissionInterceptor implements HandlerInterceptor {
    private static final String[][] studentForbiddenList = {
            {"/api/user", "POST"},
            {"/api/users", "GET"},
            {"/api/user/[\\d]+", "DELETE"},
            {"/api/user/[\\d]+", "GET"},
            {"/api/problem", "POST"},
            {"/api/problem/[\\d]+", "PATCH"},
            {"/api/assignment", "POST"},
            {"/api/assignment/[\\d]+", "DELETE"},
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        var token = TokenService.getToken(request);
        if (!token.role.equals("Researcher")) {
            return true;
        }
        var path = request.getRequestURI();
        var method = request.getMethod();
        for (var rule : studentForbiddenList) {
            if (!Pattern.matches(rule[0], path))
                continue;
            if (rule[1].equals("*") || rule[1].equals(method)) {
                response.setStatus(403);
                return false;
            }
        }
        return true;
    }
}
package com.mu.muses.intercceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mu.muses.config.RestResponse;
import com.mu.muses.enums.ResultCode;
import com.mu.muses.service.TokenService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {
//        try {
//            var token = TokenService.getToken(request);
//            if (token != null)
//                return true;
//        } catch (JWTVerificationException e) {
//            response.getWriter().print(e.getMessage());
//        }
//        response.setStatus(401);
//        response.getWriter().print(JSONObject.toJSONString(RestResponse.fail(ResultCode.TOKEN_INVALID)));
//        return false;
        return true;
    }
}

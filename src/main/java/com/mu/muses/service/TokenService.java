package com.mu.muses.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mu.muses.entity.Token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class TokenService {
    /**
     * Cookie名称
     */
    public static final String cookieName = "sessionToken";
    /**
     * Cookie默认maxAge
     */
    public static final int defaultMaxAge = 24 * 60 * 60;
    /**
     * token秘钥
     */
    public static final String secret = "Muses555555";

    /**
     * JWT生成Token.<br/>
     * <p>
     * JWT构成: header, payload, signature
     *
     * @param id 登录成功后用户id, 不可为空
     */
    public static Token createToken(int id, String role) {
        return new Token(id, role, new Date(), defaultMaxAge);
    }

    /**
     * 解密Token
     */
    public static Token authenticate(String jwt) throws JWTVerificationException {
        return Token.fromJwt(jwt, secret);
    }

    public static Token getToken(HttpServletRequest request) throws JWTVerificationException {
        var cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        Cookie target = null;
        for (var cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                target = cookie;
                break;
            }
        }
        return target == null ? null : authenticate(target.getValue());
    }

    public static void setToken(HttpServletRequest request,HttpServletResponse response, Token token) {
        Cookie cookie = new Cookie(cookieName, token.toJwt(secret));
        cookie.setMaxAge(defaultMaxAge);
        cookie.setPath("/");
        cookie.setSecure(false);

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.addCookie(cookie);
    }

}

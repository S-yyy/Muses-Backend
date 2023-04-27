package com.mu.muses.entity;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Token {
    public int id;

    public String role;

    public Date issueDate;

    public Date expireDate;

    public Token(int id, String role, Date issueDate, Date expireDate) {
        this.id = id;
        this.role = role;
        this.issueDate = issueDate;
        this.expireDate = expireDate;
    }

    public Token(int id, String role, Date issueDate, int maxAge) {
        this.id = id;
        this.role = role;
        this.issueDate = issueDate;
        var calendar = Calendar.getInstance();
        calendar.setTime(issueDate);
        calendar.add(Calendar.SECOND, maxAge);
        this.expireDate = calendar.getTime();
    }

    public static Token fromJwt(String token, String secret) throws JWTVerificationException {
        var verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        var jwt = verifier.verify(token);
        return new Token(
                jwt.getClaim("id").asInt(),
                jwt.getClaim("role").asString(),
                jwt.getIssuedAt(),
                jwt.getExpiresAt());
    }

    public String toJwt(String secret) {
        var map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                .withHeader(map)
                .withClaim("id", id)
                .withClaim("role", role)
                .withIssuedAt(issueDate)
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(secret));
    }
}

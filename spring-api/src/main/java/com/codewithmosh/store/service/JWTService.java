package com.codewithmosh.store.service;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

public class JWTService {
    public String generateToken(String username, String password) {
        final long tokenExpirationTime = 86400;

        return Jwts.builder()
                .subj

    }
}

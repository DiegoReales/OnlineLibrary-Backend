package co.edu.cuc.onlinelibrary.auth.domain.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractJwtUtil {

    protected enum CLAIMS {
        EMULATED_ID("emulatedId"),
        ROLE("role"),
        USER_ID("userId");

        private final String value;

        CLAIMS(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    protected SecretKey doGenerateSecretKey(String secret) {
        byte[] secretKeyBytes = secret.getBytes();
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }

    protected String doGenerateToken(String username, Map<String, Object> claims, int time, String secret) {
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + time * 1000L))
                .signWith(doGenerateSecretKey(secret))
                .compact();
    }


    protected Map<String, Object> generateClaims(String role, int userId) {
        return this.generateClaims(role, userId, -1);
    }

    protected Map<String, Object> generateClaims(String role, int userId, int emulatedId) {
        Map<String, Object> claims = new HashMap<>();

        claims.put(CLAIMS.ROLE.toString(), role);
        claims.put(CLAIMS.USER_ID.toString(), userId);
        claims.put(CLAIMS.EMULATED_ID.toString(), emulatedId);

        return claims;
    }

    protected Claims getClaims(String token, String secret) {
        Claims claims;
        try {
            JwtParser parser = Jwts.parser()
                    .verifyWith(doGenerateSecretKey(secret))
                    .build();

            claims = parser.parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }

    public String getSubject(Claims claims) {
        return claims.getSubject();
    }

    public int getUserId(Claims claims) {
        return claims.get(CLAIMS.USER_ID.toString(), Integer.class);
    }

    public int getEmulatedId(Claims claims) {
        return claims.get(CLAIMS.EMULATED_ID.toString(), Integer.class);
    }

    public boolean isTokenEmulated(Claims claims) {
        if (claims.containsKey(CLAIMS.EMULATED_ID.toString()))
            return (claims.get(CLAIMS.EMULATED_ID.toString(), Integer.class) > -1);
        return false;
    }

    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}

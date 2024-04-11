package co.edu.cuc.onlinelibrary.auth.domain.util;

import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtRefreshUtil extends AbstractJwtUtil {

    private static final int JWT_TOKEN_VALIDITY = 70 * 60;

    @Value("${auth.jwt.secret.refresh-token}")
    private String secret;

    public String generateToken(UserDetails userDetails, String role, int userId, int emulatedId) {
        return doGenerateToken(
                userDetails.getUsername(),
                generateClaims(role, userId, emulatedId),
                JWT_TOKEN_VALIDITY,
                secret);
    }

    public boolean validateToken(String token, UserDto userDetails) {
        Claims claims = getClaims(token);
        String username = getSubject(claims);

        // Invalid token
        return !userDetails.getUsername().equals(username) || isTokenExpired(claims);
    }

    public Claims getClaims(String token) {
        return getClaims(token, secret);
    }
}

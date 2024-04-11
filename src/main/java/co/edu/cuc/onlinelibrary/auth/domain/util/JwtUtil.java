package co.edu.cuc.onlinelibrary.auth.domain.util;

import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JwtUtil extends AbstractJwtUtil {

    private static final int JWT_TOKEN_VALIDITY = 60 * 60;

    @Value("${auth.jwt.secret.token}")
    private String secret;

    public String generateToken(UserDetails userDetails, String role, int userId, int emulatedId) {
        return doGenerateToken(
                userDetails.getUsername(),
                generateClaims(role, userId, emulatedId),
                JWT_TOKEN_VALIDITY,
                secret);
    }

    public void validateToken(String token, UserDto userDetails) {
        Claims claims = getClaims(token);
        String username = getSubject(claims);

        // Invalid token
        if (!userDetails.getUsername().equals(username) || isTokenExpired(claims))
            throw new AuthenticationServiceException("EXPIRED");

        // Allowed multiple session
        if (Boolean.TRUE.equals(userDetails.getSingleSession())) {
            boolean isCurrentToken = Objects.nonNull(userDetails.getCurrentToken())
                    && !userDetails.getCurrentToken().isBlank()
                    && userDetails.getCurrentToken().equals(token);
            if (!isCurrentToken) {
                throw new AuthenticationServiceException("SINGLE_SESSION");
            }
        }
    }

    public Claims getClaims(String token) {
        return getClaims(token, secret);
    }
}

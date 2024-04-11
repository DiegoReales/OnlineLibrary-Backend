package co.edu.cuc.onlinelibrary.auth.domain.dto.responsebody;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRefreshTokenResponseBody {
    private String username;
    private String accessToken;
    private String refreshToken;
}

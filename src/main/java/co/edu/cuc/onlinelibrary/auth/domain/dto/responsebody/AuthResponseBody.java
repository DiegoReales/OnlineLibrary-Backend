package co.edu.cuc.onlinelibrary.auth.domain.dto.responsebody;

import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponseBody {
    private UserDto user;
    private String accessToken;
    private String refreshToken;
    private Integer emulatedId;
    private Integer idleTime;
}

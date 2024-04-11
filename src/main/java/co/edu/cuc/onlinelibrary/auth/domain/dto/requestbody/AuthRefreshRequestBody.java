package co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRefreshRequestBody {

    @NotEmpty(message = "AccessToken es un campo requerido.")
    private String accessToken;

    @NotEmpty(message = "refreshToken es un campo requerido.")
    private String refreshToken;
}

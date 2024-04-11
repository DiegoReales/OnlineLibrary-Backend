package co.edu.cuc.onlinelibrary.auth.domain.service;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ChangePasswordDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.AuthRefreshRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.AuthRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.responsebody.AuthRefreshTokenResponseBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.responsebody.AuthResponseBody;

public interface IAuthService {
    AuthResponseBody login(AuthRequestBody authRequestBody, String ipRemote);

    AuthRefreshTokenResponseBody refreshToken(AuthRefreshRequestBody authRefreshRequestBody);

    void changePassword(ChangePasswordDto changePasswordDto, String username);
}

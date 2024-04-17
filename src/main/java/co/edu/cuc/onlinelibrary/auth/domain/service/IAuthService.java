package co.edu.cuc.onlinelibrary.auth.domain.service;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ChangePasswordDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.AuthRefreshRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.AuthRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.SignUpRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.responsebody.AuthRefreshTokenResponseBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.responsebody.AuthResponseBody;

public interface IAuthService {
    AuthResponseBody signIn(AuthRequestBody authRequestBody, String ipRemote);
    UserDto signUp(SignUpRequestBody requestBody);

    AuthRefreshTokenResponseBody refreshToken(AuthRefreshRequestBody authRefreshRequestBody);

    void changePassword(ChangePasswordDto changePasswordDto, String username);

}

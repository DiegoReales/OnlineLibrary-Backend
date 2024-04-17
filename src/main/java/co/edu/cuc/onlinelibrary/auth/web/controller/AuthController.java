package co.edu.cuc.onlinelibrary.auth.web.controller;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.ChangePasswordDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.DefaultResponse;
import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.AuthRefreshRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.AuthRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.SignUpRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.responsebody.AuthRefreshTokenResponseBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.responsebody.AuthResponseBody;
import co.edu.cuc.onlinelibrary.auth.domain.enums.ActionLogEnum;
import co.edu.cuc.onlinelibrary.auth.domain.service.IAuthService;
import co.edu.cuc.onlinelibrary.auth.domain.util.AuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final String MODULE = "AUTH";
    private final IAuthService authService;
    private final AuthUtil authUtil;

    @PostMapping(value = "/signin")
    public ResponseEntity<AuthResponseBody> signIn(@RequestBody @Valid AuthRequestBody authRequestBody, HttpServletRequest request) {
        String ipRemote = authUtil.getTrueClientIp();
        AuthResponseBody authResponseBody = authService.signIn(authRequestBody, ipRemote);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("SIGNIN")
                .message("Inicio de sesión para usuario: " + authRequestBody.getUsername())
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(authResponseBody);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<DefaultResponse> signUp(@RequestBody @Valid SignUpRequestBody requestBody, HttpServletRequest request) {
        String ipRemote = authUtil.getTrueClientIp();
        UserDto user = authService.signUp(requestBody);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("SIGNUP")
                .message("Registro de usuario: " + user.getUsername() + " desde: " + ipRemote)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(DefaultResponse.createOkMessage());
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<AuthRefreshTokenResponseBody> login(@RequestBody @Valid AuthRefreshRequestBody authRefreshRequestBody, HttpServletRequest request) {
        AuthRefreshTokenResponseBody authRefreshTokenResponseBody = authService.refreshToken(authRefreshRequestBody);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("REFRESH")
                .message("Refrescar token para usuario: " + authRefreshTokenResponseBody.getUsername())
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(authRefreshTokenResponseBody);
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<DefaultResponse> login(
            @RequestBody @Valid ChangePasswordDto changePasswordDto,
            Authentication authentication, HttpServletRequest request) {

        String username = authentication.getName();
        authService.changePassword(changePasswordDto, username);

        ActionLogDto actionLogDTO = ActionLogDto.builder()
                .module(MODULE).action("LOGIN")
                .message("Cambio de contaseña: " + username)
                .build();

        request.setAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString(), actionLogDTO);
        return ResponseEntity.ok(DefaultResponse.createOkMessage());
    }

    @PreAuthorize("hasAuthority('auth:basic')")
    @PostMapping(value = "/check")
    public ResponseEntity<Map<String, Object>> check(Authentication authentication) {
        Map<String, Object> response = Map.of(
                "status", "ok",
                "username", authentication.getName(),
                "details", authentication.getDetails(),
                "permissions", authentication.getAuthorities()
        );
        return ResponseEntity.ok(response);
    }
}

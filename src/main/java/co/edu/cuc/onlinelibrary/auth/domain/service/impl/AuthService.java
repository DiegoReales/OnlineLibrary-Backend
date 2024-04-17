package co.edu.cuc.onlinelibrary.auth.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ChangePasswordDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.MenuBranchDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.PasswordRecoveryDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.AuthRefreshRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.AuthRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.SignUpRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.requestbody.UserRequestBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.responsebody.AuthRefreshTokenResponseBody;
import co.edu.cuc.onlinelibrary.auth.domain.dto.responsebody.AuthResponseBody;
import co.edu.cuc.onlinelibrary.auth.domain.enums.AuthParams;
import co.edu.cuc.onlinelibrary.auth.domain.service.IAuthService;
import co.edu.cuc.onlinelibrary.auth.domain.service.IAuthUserDetailService;
import co.edu.cuc.onlinelibrary.auth.domain.service.IGoogleRecaptchaService;
import co.edu.cuc.onlinelibrary.auth.domain.service.IUserService;
import co.edu.cuc.onlinelibrary.auth.domain.util.JwtRefreshUtil;
import co.edu.cuc.onlinelibrary.auth.domain.util.JwtUtil;
import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpBadRequestException;
import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpGenericException;
import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpUnAuthorizedException;
import co.edu.cuc.onlinelibrary.parameter.domain.service.IParameterService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private static final int SIGNUP_DEFAULT_ROLE = 2;

    private final IAuthUserDetailService userDetailService;
    private final IUserService userService;
    private final IGoogleRecaptchaService googleRecaptchaService;
    private final IParameterService parameterService;
    private final JwtUtil jwtUtil;
    private final JwtRefreshUtil jwtRefreshUtil;
    private final PasswordEncoder passwordEncoder;

    @Value("${auth.google.recaptcha.enable}")
    private boolean isEnableGoogleRecaptcha;
    
    @Override
    public AuthResponseBody signIn(AuthRequestBody auth, String ipRemote) {
        if (isEnableGoogleRecaptcha) validateRecaptchaToken(auth.getRecaptchaToken(), ipRemote);
        UserDto userDetails = (UserDto) userDetailService.loadUserByUsername(auth.getUsername());
        // Check Inactive user
        if (!userDetails.isEnabled())
            throw new BadCredentialsException(String.format("[%s:login] Inactive user ", auth.getUsername()));


        // Check Invalid Password
        if (!passwordEncoder.matches(auth.getPassword(), userDetails.getPassword())){
            PasswordRecoveryDto passwordRecovery = userDetailService.findPasswordRecovery(userDetails.getId())
                    .filter(e -> passwordEncoder.matches(auth.getPassword(), e.getPassword()))
                    .orElseThrow(() -> new BadCredentialsException(
                            String.format("[%s:login] Invalid password", auth.getUsername())));

            passwordRecovery.setRecovered(true);
            userDetailService.savePasswordRecovery(passwordRecovery);
            userDetails.setChangePassword(true);
        }

        // Build AuthResponse
        Collection<String> permissions = userDetailService.getPermissionsByRoleId(userDetails.getRoleId());
        userDetails.setPermissions(permissions);

        Collection<MenuBranchDto> menu = userDetailService.getMenuTreeByRoleId(userDetails.getRoleId());
        userDetails.setMenu(menu);

        // Set Authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthResponseBody authResponseBody = generateAuthResponse(userDetails);
        userDetails.setCurrentToken(authResponseBody.getAccessToken());
        userDetailService.updateCurrentToken(userDetails.getUsername(), authResponseBody.getAccessToken());
        return authResponseBody;
    }

    @Override
    public UserDto signUp(SignUpRequestBody req) {
        UserRequestBody userRequestBody = new UserRequestBody();

        userRequestBody.setUsername(req.getEmail());
        userRequestBody.setPassword(req.getPassword());
        userRequestBody.setPasswordConfirm(req.getPasswordConfirm());
        userRequestBody.setDni(req.getDni());
        userRequestBody.setName(req.getName());
        userRequestBody.setLastname(req.getLastname());
        userRequestBody.setRoleId(SIGNUP_DEFAULT_ROLE);
        userRequestBody.setActive(true);

        return userService.create(userRequestBody);
    }

    @Override
    public AuthRefreshTokenResponseBody refreshToken(AuthRefreshRequestBody authRefreshRequestBody) {
        try {
            String accessToken = authRefreshRequestBody.getAccessToken();
            String refreshToken = authRefreshRequestBody.getRefreshToken();

            Claims claims = jwtRefreshUtil.getClaims(refreshToken);
            String username = jwtRefreshUtil.getSubject(claims);
            int emulatedId = jwtRefreshUtil.getEmulatedId(claims);

            if (jwtRefreshUtil.isTokenExpired(claims)) {
                throw new HttpUnAuthorizedException("Token no es valido", false);
            }

            UserDto user = (UserDto) userDetailService.loadUserByUsername(username);

            if (Boolean.TRUE.equals(user.getSingleSession()) && !user.getCurrentToken().equals(accessToken)) {
                throw new HttpUnAuthorizedException("Token no es valido", false);
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            accessToken = jwtUtil.generateToken(user, user.getRole().getCode(), user.getId(), emulatedId);
            refreshToken = jwtRefreshUtil.generateToken(user, user.getRole().getCode(), user.getId(), emulatedId);

            userDetailService.updateCurrentToken(username, accessToken);

            return AuthRefreshTokenResponseBody.builder()
                    .username(username)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (JwtException e) {
            throw new HttpUnAuthorizedException(e.getMessage(), false);
        }

    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordDto changePasswordDto, String username) {
        if (!Objects.equals(changePasswordDto.getNewPassword(), changePasswordDto.getConfirmNewPassword())) {
            throw new HttpBadRequestException("Las confirmación de contraseña es diferente.");
        }

        if (Objects.equals(changePasswordDto.getCurrentPassword(), changePasswordDto.getNewPassword())) {
            throw new HttpBadRequestException("Las contraseñas deben ser diferentes.");
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        if (!passwordEncoder.matches(changePasswordDto.getCurrentPassword(), userDetails.getPassword())) {
            throw new HttpBadRequestException("La contraseña actual es incorrecta.");
        }

        String newPasswordEncode = passwordEncoder.encode(changePasswordDto.getNewPassword());
        userDetailService.changePassword(userDetails.getUsername(), newPasswordEncode);
    }

    private AuthResponseBody generateAuthResponse(UserDto user) {
        return AuthResponseBody.builder()
                .accessToken(jwtUtil.generateToken(user, user.getRole().getCode(), user.getId(), -1))
                .refreshToken(jwtRefreshUtil.generateToken(user, user.getRole().getCode(), user.getId(), -1))
                .user(user).emulatedId(-1)
                .build();
    }

    private void validateRecaptchaToken(String recaptchaToken, String ipAddress) throws HttpGenericException {
        boolean enabled = parameterService.getBooleanValueByKey(AuthParams.AUTH_VGR);
        if (enabled && !googleRecaptchaService.validateToken(recaptchaToken, ipAddress)) {
            throw new HttpGenericException(HttpStatus.BAD_REQUEST, "Request rejected by google recaptcha.");
        }
    }
}

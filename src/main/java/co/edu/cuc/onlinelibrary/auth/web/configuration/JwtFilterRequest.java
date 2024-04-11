package co.edu.cuc.onlinelibrary.auth.web.configuration;

import co.edu.cuc.onlinelibrary.auth.domain.dto.ActionLogDto;
import co.edu.cuc.onlinelibrary.auth.domain.dto.AuthDetails;
import co.edu.cuc.onlinelibrary.auth.domain.dto.UserDto;
import co.edu.cuc.onlinelibrary.auth.domain.enums.ActionLogEnum;
import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpUnAuthorizedException;
import co.edu.cuc.onlinelibrary.auth.domain.service.IActionLogService;
import co.edu.cuc.onlinelibrary.auth.domain.service.IAuthUserDetailService;
import co.edu.cuc.onlinelibrary.auth.domain.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilterRequest extends OncePerRequestFilter {

    private final IAuthUserDetailService userDetailService;
    private final IActionLogService actionLogService;
    private final JwtUtil jwtUtil;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String PREFIX_TOKEN = "Bearer";
    private static final String NO_AUTHENTICATION = "NO_AUTHENTICATION";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Instant start = Instant.now();

        try {
            checkAuthorizationToken(request);
        } catch (AuthenticationServiceException e) {
            UnauthorizedEntryPoint.resolver(response, e);
            return;
        }

        filterChain.doFilter(request, response);
        registerActionLog(request, response);

        Instant end = Instant.now();
        Long millis = Duration.between(start, end).toMillis();
        log.info("response at: {}", millis );
    }

    private void checkAuthorizationToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        Set<String> uriWhiteList = Arrays.stream(SecurityConfiguration.URI_WHITE_LIST).collect(Collectors.toSet());

        if (Objects.isNull(authorizationHeader)
            || !authorizationHeader.startsWith(PREFIX_TOKEN)
            || uriWhiteList.contains(request.getRequestURI())) {
            return;
        }

        UsernamePasswordAuthenticationToken authToken = null;
        String token = authorizationHeader.substring(PREFIX_TOKEN.length()).trim();
        try {
            Claims claims = jwtUtil.getClaims(token);
            String username = jwtUtil.getSubject(claims);

            UserDto userDetails = (UserDto) userDetailService.loadUserByUsername(username);
            jwtUtil.validateToken(token, userDetails);

            Collection<String> permissions = userDetailService.getPermissionsByRoleId(userDetails.getRoleId());
            userDetails.setPermissions(permissions);

            authToken = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(), token, userDetails.getAuthorities());

            AuthDetails authDetails = new AuthDetails();
            authDetails.setUserId(userDetails.getId());
            authDetails.setEmulatedId(jwtUtil.getEmulatedId(claims));

            authToken.setDetails(authDetails);
        } catch (JwtException e) {
            throw new HttpUnAuthorizedException(String.format("[Invalid JWT]: %s", e.getMessage()));
        }

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private void registerActionLog(HttpServletRequest req, HttpServletResponse res) {
        if (Objects.isNull(req.getAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString()))) return;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = Objects.nonNull(authentication) ? authentication.getName() : NO_AUTHENTICATION ;
        ActionLogDto actionLog = (ActionLogDto) req.getAttribute(ActionLogEnum.ATTRIBUTE_NAME.toString());

        actionLog.setUsername(username);
        actionLog.setIpAddress(getTrueClientIp(req));
        actionLog.setIpLocal(req.getLocalAddr());
        actionLog.setMethod(req.getMethod());
        actionLog.setEndpoint(req.getRequestURI());
        actionLog.setResponseStatus(res.getStatus());

        actionLogService.save(actionLog);
    }

    private String getTrueClientIp(HttpServletRequest req) {
        String ip = req.getHeader("True-Client-IP");
        if (ip == null || ip.isEmpty() || ip.equalsIgnoreCase("unknown")) {
            ip = req.getRemoteAddr();
        }
        return ip;
    }
}

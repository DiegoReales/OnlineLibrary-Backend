package co.edu.cuc.onlinelibrary.auth.domain.util;

import co.edu.cuc.onlinelibrary.auth.domain.dto.AuthDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public final HttpServletRequest servletRequest;

    public AuthUtil(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    private AuthDetails getAuthDetails(Authentication authentication) {
        return (AuthDetails) authentication.getDetails();
    }

    public int getUserId(Authentication authentication) {
        return getAuthDetails(authentication).getUserId();
    }

    public boolean hasPermission(Authentication authentication, String authority) {
        return authentication.getAuthorities().stream()
                .anyMatch(e -> e.getAuthority().equalsIgnoreCase(authority));
    }

    public boolean isSessionEmulated(Authentication authentication) {
        return getAuthDetails(authentication).isEmulated();
    }

    public String getTrueClientIp() {
        String ip = servletRequest.getHeader("True-Client-IP");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = servletRequest.getRemoteAddr();
        }
        return ip;
    }
}

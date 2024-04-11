package co.edu.cuc.onlinelibrary.auth.web.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        resolver(response, authException);
    }

    public static void resolver(HttpServletResponse response, AuthenticationException authException) throws IOException  {
        log.error(authException.toString());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        StringBuilder message = new StringBuilder();

        if (authException instanceof AuthenticationServiceException) {
            message.append(authException.getMessage());
        } else {
            if (authException.getCause() != null)
                message.append(authException.getCause().toString());
            message.append(authException.getMessage());
        }

        byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message.toString()));
        response.getOutputStream().write(body);
    }
}

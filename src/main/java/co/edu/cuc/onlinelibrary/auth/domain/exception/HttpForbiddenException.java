package co.edu.cuc.onlinelibrary.auth.domain.exception;

import org.springframework.http.HttpStatus;

public class HttpForbiddenException extends HttpGenericException {

    public HttpForbiddenException(String statusText) {
        super(HttpStatus.FORBIDDEN, statusText);
    }
}

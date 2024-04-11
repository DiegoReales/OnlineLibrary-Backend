package co.edu.cuc.onlinelibrary.auth.domain.exception;

import org.springframework.http.HttpStatus;

public class HttpNotFoundException extends HttpGenericException {

    public HttpNotFoundException(String statusText) {
        super(HttpStatus.NOT_FOUND, statusText);
    }
}

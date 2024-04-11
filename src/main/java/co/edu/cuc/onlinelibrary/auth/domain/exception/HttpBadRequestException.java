package co.edu.cuc.onlinelibrary.auth.domain.exception;

import org.springframework.http.HttpStatus;

public class HttpBadRequestException extends HttpGenericException {

    public HttpBadRequestException(String statusText) {
        super(HttpStatus.BAD_REQUEST, statusText);
    }
}

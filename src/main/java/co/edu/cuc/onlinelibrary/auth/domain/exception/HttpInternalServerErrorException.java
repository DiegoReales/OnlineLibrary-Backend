package co.edu.cuc.onlinelibrary.auth.domain.exception;

import org.springframework.http.HttpStatus;

public class HttpInternalServerErrorException extends HttpGenericException {

    public HttpInternalServerErrorException(String statusText) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, statusText);
    }
}

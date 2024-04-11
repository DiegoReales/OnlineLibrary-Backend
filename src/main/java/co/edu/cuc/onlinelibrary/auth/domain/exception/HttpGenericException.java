package co.edu.cuc.onlinelibrary.auth.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Map;

public class HttpGenericException extends HttpStatusCodeException {

    public HttpGenericException(HttpStatus status, String statusText) {
        super(status, statusText);
    }

    public Map<String, Object> getErrorData() {
        return Map.of(
                "type", "error",
                "message", this.getStatusText());
    }
}

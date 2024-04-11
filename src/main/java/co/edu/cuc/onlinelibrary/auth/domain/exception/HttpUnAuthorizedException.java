package co.edu.cuc.onlinelibrary.auth.domain.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class HttpUnAuthorizedException extends HttpGenericException {
    private final boolean canRefresh;

    public HttpUnAuthorizedException(String statusText) {
        super(HttpStatus.UNAUTHORIZED, statusText);
        this.canRefresh = true;
    }

    public HttpUnAuthorizedException(String statusText, boolean canRefresh) {
        super(HttpStatus.UNAUTHORIZED, statusText);
        this.canRefresh = canRefresh;
    }

    @Override
    public Map<String, Object> getErrorData() {
        return Map.of(
                "type", "error",
                "message", this.getStatusText(),
                "canRefresh", this.canRefresh
        );
    }
}

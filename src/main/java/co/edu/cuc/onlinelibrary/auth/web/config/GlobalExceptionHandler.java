package co.edu.cuc.onlinelibrary.auth.web.config;

import co.edu.cuc.onlinelibrary.auth.domain.exception.HttpGenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    static final String MESSAGE = "message";

    static final String ERROR = "error";
    static final String BAD_CREDENTIALS = "Credenciales no validas.";
    
    @ExceptionHandler(value = HttpGenericException.class)
    protected ResponseEntity<Object> handleHttpGenericException(HttpGenericException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getErrorData(), new HttpHeaders(), exception.getStatusCode(), request);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception, WebRequest request) {
        Map<String, String> errorData = Map.of(
                "type", ERROR,
                MESSAGE, BAD_CREDENTIALS);
        return handleExceptionInternal(exception, errorData, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException exception, WebRequest request) {
        Map<String, String> errorData = Map.of(
                "type", ERROR,
                MESSAGE, BAD_CREDENTIALS);
        return handleExceptionInternal(exception, errorData, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = SQLException.class)
    protected ResponseEntity<Object> handleSQLException(SQLException exception, WebRequest request) {
        Map<String, String> errorData = Map.of(
                "type", ERROR,
                MESSAGE, exception.getMessage());
        return handleExceptionInternal(exception, errorData, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
        Map<String, String> errorData = Map.of(
                "type", ERROR,
                MESSAGE, "Usted no cuenta con privilegios para ejecutar esta acción.");
        return handleExceptionInternal(exception, errorData, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> fields = ex.getBindingResult().getAllErrors()
                .stream()
                .reduce(new HashMap<>(),
                        (errorMap, currentError) -> {
                            String key = currentError.getCode();
                            if (currentError instanceof FieldError) {
                                key = ((FieldError) currentError).getField();
                            }
                            String message = currentError.getDefaultMessage();
                            errorMap.put(key, message);
                            return errorMap;
                        },
                        (map1, map2) -> map1);

        Map<String, Object> errorData = Map.of(
                "type", ERROR,
                MESSAGE, "No se pudo procesar su solicitud. Por favor revise el detalle de los errores que se presentaron",
                "fields", fields);
        return handleExceptionInternal(ex, errorData, headers, status, request);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleSQLException(Exception exception, WebRequest request) {
        Map<String, String> errorData = Map.of(
                "type", ERROR,
                MESSAGE, exception.getMessage());

        // exception.printStackTrace();
        return handleExceptionInternal(exception, errorData, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}

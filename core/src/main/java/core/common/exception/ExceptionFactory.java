package core.common.exception;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.naming.AuthenticationException;
import java.util.Arrays;

public enum ExceptionFactory {
    METHOD_ARGUMNET_INVALID(MethodArgumentNotValidException.class, StatusCode.INVALID_PARAMETER),
    MISSING_REQUEST_PARAMETER(MissingServletRequestParameterException.class, StatusCode.MISSING_PARAMETER),
    HTTP_METHOD_NOT_SUPPORTED(HttpRequestMethodNotSupportedException.class, StatusCode.INVALID_METHOD),
    ILLEGAL_ARGUMENT(IllegalArgumentException.class, StatusCode.ILLEGAL_ARGUMENT),
    AUTH_EXCEPTION(AuthenticationException.class, StatusCode.UNAUTH),
    SERVER_EXCEPTION(Exception.class, StatusCode.SERVER_ERROR);

    private final Class<? extends Exception> clazz;
    private final StatusCode statusCode;

    ExceptionFactory(Class<? extends Exception> clazz, StatusCode statusCode) {
        this.clazz = clazz;
        this.statusCode = statusCode;
    }

    public static StatusCode getInstance(Exception e) {
        return Arrays.stream(ExceptionFactory.values())
                .filter(v -> v.clazz.isInstance(e))
                .findFirst()
                .orElse(SERVER_EXCEPTION).statusCode;
    }

}

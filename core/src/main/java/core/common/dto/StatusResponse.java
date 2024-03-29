package core.common.dto;

import core.common.exception.StatusCode;
import lombok.Getter;

@Getter
public class StatusResponse {
    private String message;
    private String code;

    public StatusResponse(StatusCode statusCode) {
        this.message = statusCode.getMessage();
        this.code = statusCode.getCode();
    }
}

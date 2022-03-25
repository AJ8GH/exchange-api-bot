package io.github.aj8gh.bot.domain.heartbeat.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
public class APINGException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String errorDetails;
    private final String requestUUID;

    public APINGException(ErrorCode errorCode, String errorDetails, String requestUUID) {
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.requestUUID = requestUUID;
    }

    public APINGException(String message, ErrorCode errorCode, String errorDetails, String requestUUID) {
        super(message);
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.requestUUID = requestUUID;
    }

    @Getter
    @RequiredArgsConstructor
    public enum ErrorCode {
        INVALID_INPUT_DATA("Invalid input data"),
        INVALID_SESSION_INFORMATION("The session token passed is invalid"),
        NO_APP_KEY("An application key is required for this operation"),
        NO_SESSION("A session token is required for this operation"),
        INVALID_APP_KEY("The application key passed is invalid"),
        UNEXPECTED_ERROR("An unexpected internal error occurred that prevented successful request processing.");

        private final String message;
    }
}

package io.github.aj8gh.bot.domain.racestatus.exceptions;

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
        UNEXPECTED_ERROR("The operation failed with an unexpected error"),
        INVALID_INPUT_DATA("Invalid input data"),
        INVALID_SESSION_INFORMATION("The session token passed is invalid or expired"),
        INVALID_APP_KEY("The application key passed is invalid"),
        SERVICE_BUSY("The service is currently too busy to service this request"),
        TIMEOUT_ERROR("Internal call to downstream service timed out"),
        NO_SESSION("A session token is required for this operation"),
        NO_APP_KEY("An application key is required for this operation"),
        TOO_MANY_REQUESTS("Too many requests"),
        SERVICE_UNAVAILABLE("Service is currently unavailable");

        private final String message;
    }
}

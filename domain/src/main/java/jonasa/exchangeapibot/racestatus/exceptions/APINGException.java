package jonasa.exchangeapibot.racestatus.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APINGException {

    private ErrorCode errorCode;

    private String errorDetails;

    private String requestUUid;

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

package jonasa.exchangeapibot.heartbeat.exceptions;

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

    private String requestUUID;

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

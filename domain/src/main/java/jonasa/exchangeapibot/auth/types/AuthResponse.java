package jonasa.exchangeapibot.auth.types;

import jonasa.exchangeapibot.auth.enums.AuthErrorCode;
import jonasa.exchangeapibot.auth.enums.AuthStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;

    private String product;

    private AuthStatus status;

    private AuthErrorCode error;
}

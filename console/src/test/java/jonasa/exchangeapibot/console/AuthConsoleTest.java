package jonasa.exchangeapibot.console;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jonasa.exchangeapibot.auth.client.AuthClient;
import jonasa.exchangeapibot.auth.enums.AuthStatus;
import jonasa.exchangeapibot.auth.types.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AuthConsoleTest {
    private static final AuthResponse AUTH_RESPONSE = AuthResponse.builder()
            .status(AuthStatus.SUCCESS)
            .product("product")
            .token("token")
            .build();

    @Mock
    private AuthClient authClient;
    private AuthConsole authConsole;

    @BeforeEach
    void setUp() {
        openMocks(this);
        authConsole = new AuthConsole(authClient);
    }

    @Test
    void login_Success_ReturnsResponse() {
        when(authClient.login())
                .thenReturn(Optional.of(AUTH_RESPONSE));
        String response = authConsole.login();
        verify(authClient).login();
        assertEquals(AUTH_RESPONSE.toString(), response);
    }
}

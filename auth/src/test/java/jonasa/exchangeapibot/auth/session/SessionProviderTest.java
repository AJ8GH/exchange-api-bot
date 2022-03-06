package jonasa.exchangeapibot.auth.session;

import jonasa.exchangeapibot.auth.client.AuthClient;
import jonasa.exchangeapibot.auth.enums.AuthErrorCode;
import jonasa.exchangeapibot.auth.types.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Clock;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class SessionProviderTest {
    private static final String INITIAL_TOKEN = "initialToken";
    private static final String NEW_TOKEN = "newToken";
    private static final long sessionTtl = 1000;
    private static final long keepAliveFreq = 500;

    private SessionProvider sessionProvider;
    @Mock
    private AuthClient authClient;
    @Mock
    private Clock clock;

    @BeforeEach
    void setUp() {
        openMocks(this);
        sessionProvider = new SessionProvider(
                authClient, clock, sessionTtl, keepAliveFreq
        );
    }

    @Test
    void get_SessionActive_ReturnsExistingToken() {
        when(authClient.login()).thenReturn(Optional.of(AuthResponse.builder()
                .token(INITIAL_TOKEN)
                .build()));
        sessionProvider.get();

        when(authClient.login()).thenReturn(Optional.of(AuthResponse.builder()
                .token(NEW_TOKEN)
                .build()));

        var token = sessionProvider.get();

        verify(authClient, times(1)).login();
        verifyNoMoreInteractions(authClient);
        assertEquals(INITIAL_TOKEN, token);
    }

    @Test
    void get_SessionExpired_ReturnsNewToken() {
        when(authClient.login()).thenReturn(Optional.of(AuthResponse.builder()
                .token(INITIAL_TOKEN)
                .build()));
        sessionProvider.get();

        when(clock.millis()).thenReturn(sessionTtl);

        when(authClient.login()).thenReturn(Optional.of(AuthResponse.builder()
                .token(NEW_TOKEN)
                .build()));

        var token = sessionProvider.get();

        verify(authClient, times(2)).login();
        assertEquals(NEW_TOKEN, token);
    }

    @Test
    void get_Fail_ReturnsNull() {
        when(authClient.login()).thenReturn(Optional.of(AuthResponse.builder()
                .error(AuthErrorCode.FORBIDDEN)
                .build()));

        var token = sessionProvider.get();

        verify(authClient).login();
        assertNull(token);
    }
}

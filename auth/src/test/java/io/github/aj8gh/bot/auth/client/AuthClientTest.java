package io.github.aj8gh.bot.auth.client;

import io.github.aj8gh.bot.domain.auth.enums.AuthErrorCode;
import io.github.aj8gh.bot.domain.auth.enums.AuthStatus;
import io.github.aj8gh.bot.domain.auth.types.AuthResponse;
import io.github.aj8gh.bot.http.util.Headers;
import io.github.aj8gh.bot.http.client.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static io.github.aj8gh.bot.http.operations.AuthOperations.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AuthClientTest {
    private static final String LOGIN_QUERY = buildLoginQuery();
    private static final String LOGOUT_PATH = LOGOUT.path();
    private static final String KEEP_ALIVE_PATH = KEEP_ALIVE.path();
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String TOKEN = "token";
    private static final String APP_KEY = "appKey";
    @Mock
    private HttpClient httpClient;
    private AuthClient authClient;

    @BeforeEach
    void setUp() {
        openMocks(this);
        authClient = new AuthClient(httpClient, LOGIN_QUERY);
    }

    @Test
    void login_Success_SendsRequestAndReturnsResponse(){
        var expectedResponse = AuthResponse.builder()
                .token(TOKEN)
                .product(APP_KEY)
                .status(AuthStatus.SUCCESS)
                .build();

        when(httpClient.post(eq(LOGIN_QUERY), any(), eq(AuthResponse.class)))
                .thenReturn(Optional.of(expectedResponse));

        var response = authClient.login();

        verify(httpClient).post(eq(LOGIN_QUERY), any(), eq(AuthResponse.class));
        assertTrue(response.isPresent(), "Expected response to be present");
        assertEquals(expectedResponse, response.get());
    }

    @Test
    void login_Error_ReturnsEmptyOptional(){
                                            var expectedResponse = AuthResponse.builder()
                                                    .product(APP_KEY)
                                                    .error(AuthErrorCode.INTERNAL_ERROR)
                                                    .status(AuthStatus.FAIL)
                                                    .build();

        when(httpClient.post(eq(LOGIN_QUERY), any(), eq(AuthResponse.class)))
                .thenReturn(Optional.of(expectedResponse));

        var response = authClient.login();

        verify(httpClient).post(eq(LOGIN_QUERY), any(), eq(AuthResponse.class));
        assertTrue(response.isEmpty(), "Expected response to be empty");
    }

    @ParameterizedTest
    @MethodSource(value = "pathProvider")
    void keepAlive_Logout_Success_ReturnsResponse(String path) {
        var expectedResponse = AuthResponse.builder()
                .token(TOKEN)
                .product(APP_KEY)
                .status(AuthStatus.SUCCESS)
                .build();

        var expectedHeaders = new HttpHeaders();
        expectedHeaders.add(Headers.X_AUTHENTICATION.header(), TOKEN);
        var expectedRequest = new HttpEntity<>(expectedHeaders);

        when(httpClient.post(eq(path), any(), eq(AuthResponse.class)))
                .thenReturn(Optional.of(expectedResponse));

        var response = path.equals(KEEP_ALIVE_PATH) ?
                authClient.keepAlive(TOKEN) :
                authClient.logout(TOKEN);

        assertTrue(response.isPresent(), "Expected response to be present");
        assertEquals(expectedResponse, response.get());
        verify(httpClient).post(path, expectedRequest, AuthResponse.class);
    }

    @ParameterizedTest
    @MethodSource(value = "pathProvider")
    void keepAlive_Logout_Error_ReturnsEmptyOptional(String path) {
        var expectedResponse = AuthResponse.builder()
                .product(APP_KEY)
                .error(AuthErrorCode.INTERNAL_ERROR)
                .status(AuthStatus.FAIL)
                .build();

        var expectedHeaders = new HttpHeaders();
        expectedHeaders.add(Headers.X_AUTHENTICATION.header(), TOKEN);
        var expectedRequest = new HttpEntity<>(expectedHeaders);

        when(httpClient.post(eq(path), any(), eq(AuthResponse.class)))
                .thenReturn(Optional.of(expectedResponse));

        var response = path.equals(KEEP_ALIVE_PATH) ?
                authClient.keepAlive(TOKEN) :
                authClient.logout(TOKEN);

        assertTrue(response.isEmpty(), "Expected response to be empty");
        verify(httpClient).post(path, expectedRequest, AuthResponse.class);
    }

    private static Collection<String> pathProvider() {
        return List.of(KEEP_ALIVE_PATH, LOGOUT_PATH);
    }

    private static String buildLoginQuery() {
        return UriComponentsBuilder.fromUriString(LOGIN.path())
                .queryParam(USERNAME, USERNAME)
                .queryParam(PASSWORD, PASSWORD)
                .toUriString();
    }
}

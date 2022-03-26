package io.github.aj8gh.bot.auth.client;

import io.github.aj8gh.bot.domain.auth.enums.AuthErrorCode;
import io.github.aj8gh.bot.domain.auth.enums.AuthStatus;
import io.github.aj8gh.bot.domain.auth.types.AuthResponse;
import io.github.aj8gh.bot.domain.util.Headers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static io.github.aj8gh.bot.auth.util.AuthOperations.KEEP_ALIVE;
import static io.github.aj8gh.bot.auth.util.AuthOperations.LOGIN;
import static io.github.aj8gh.bot.auth.util.AuthOperations.LOGOUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AuthClientMockTest {
    private static final String ROOT_URI = "http://localhost:3000/api/";
    private static final String LOGIN_QUERY = buildLoginQuery();
    private static final String LOGOUT_PATH = LOGOUT.path();
    private static final String KEEP_ALIVE_PATH = KEEP_ALIVE.path();
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String TOKEN = "token";
    private static final String APP_KEY = "appKey";
    @Mock
    private RestTemplate restTemplate;
    private AuthClient authClient;

    @BeforeEach
    void setUp() {
        openMocks(this);
        authClient = new AuthClient(restTemplate, LOGIN_QUERY);
    }

    @Test
    void login_Success_SendsRequestAndReturnsResponse(){
        var expectedResponse = AuthResponse.builder()
                .token(TOKEN)
                .product(APP_KEY)
                .status(AuthStatus.SUCCESS)
                .build();

        when(restTemplate.postForObject(eq(LOGIN_QUERY), any(), eq(AuthResponse.class)))
                .thenReturn(expectedResponse);
        when(restTemplate.getUriTemplateHandler())
                .thenReturn(new RootUriTemplateHandler(ROOT_URI));

        var response = authClient.login();

        verify(restTemplate).postForObject(eq(LOGIN_QUERY), any(), eq(AuthResponse.class));
        assertTrue(response.isPresent(), "Expected response to be present");
        assertEquals(expectedResponse, response.get());
    }

    @ParameterizedTest
    @MethodSource(value = "exceptionSource")
    void login_FailWithException_ReturnsEmptyOptional(Exception e) {
        when(restTemplate.postForObject(eq(LOGIN_QUERY), any(), eq(AuthResponse.class)))
                .thenThrow(e);
        when(restTemplate.getUriTemplateHandler())
                .thenReturn(new RootUriTemplateHandler(ROOT_URI));

        var response = authClient.login();

        assertTrue(response.isEmpty(), "Expected response to be empty");
        verify(restTemplate).postForObject(eq(LOGIN_QUERY), any(), eq(AuthResponse.class));
    }

    @Test
    void login_AuthError_ReturnsEmptyOptional() {
        when(restTemplate.postForObject(eq(LOGIN_QUERY), any(), eq(AuthResponse.class)))
                .thenReturn(AuthResponse.builder()
                        .error(AuthErrorCode.PENDING_AUTH)
                        .build());
        when(restTemplate.getUriTemplateHandler())
                .thenReturn(new RootUriTemplateHandler(ROOT_URI));

        var response = authClient.login();

        assertTrue(response.isEmpty(), "Expected response to be empty");
        verify(restTemplate).postForObject(eq(LOGIN_QUERY), any(), eq(AuthResponse.class));
    }

    @Test
    void keepAlive_Success_ReturnsResponse() {
        var expectedResponse = AuthResponse.builder()
                .token(TOKEN)
                .product(APP_KEY)
                .status(AuthStatus.SUCCESS)
                .build();

        var expectedHeaders = new HttpHeaders();
        expectedHeaders.add(Headers.X_AUTHENTICATION.header(), TOKEN);
        var expectedRequest = new HttpEntity<>(expectedHeaders);

        when(restTemplate.postForObject(KEEP_ALIVE_PATH, expectedRequest, AuthResponse.class))
                .thenReturn(expectedResponse);

        var response = authClient.keepAlive(TOKEN);

        assertTrue(response.isPresent(), "Expected response to be present");
        assertEquals(expectedResponse, response.get());
        verify(restTemplate).postForObject(KEEP_ALIVE_PATH, expectedRequest, AuthResponse.class);
    }

    @Test
    void logout_Success_ReturnsResponse() {
        var expectedResponse = AuthResponse.builder()
                .token(TOKEN)
                .product(APP_KEY)
                .status(AuthStatus.SUCCESS)
                .build();

        var expectedHeaders = new HttpHeaders();
        expectedHeaders.add(Headers.X_AUTHENTICATION.header(), TOKEN);
        var expectedRequest = new HttpEntity<>(expectedHeaders);

        when(restTemplate.postForObject(LOGOUT_PATH, expectedRequest, AuthResponse.class))
                .thenReturn(expectedResponse);

        var response = authClient.logout(TOKEN);

        assertTrue(response.isPresent(), "Expected response to be present");
        assertEquals(expectedResponse, response.get());
        verify(restTemplate).postForObject(LOGOUT_PATH, expectedRequest, AuthResponse.class);
    }

    private static List<Exception> exceptionSource() {
        return List.of(
                new RuntimeException("Test RunTime Exception"),
                new RestClientResponseException("Test RestClient Exception",
                        404, "Not Found", null, null, null)
        );
    }

    private static String buildLoginQuery() {
        return UriComponentsBuilder.fromUriString(LOGIN.path())
                .queryParam(USERNAME, USERNAME)
                .queryParam(PASSWORD, PASSWORD)
                .toUriString();
    }
}

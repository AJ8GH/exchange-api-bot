package jonasa.exchangeapibot.auth.client;

import jonasa.exchangeapibot.auth.enums.AuthErrorCode;
import jonasa.exchangeapibot.auth.enums.AuthStatus;
import jonasa.exchangeapibot.auth.types.AuthResponse;
import jonasa.exchangeapibot.auth.util.Headers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static jonasa.exchangeapibot.auth.util.AuthOperations.KEEP_ALIVE;
import static jonasa.exchangeapibot.auth.util.AuthOperations.LOGIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AuthClientMockTest {
    private static final String AUTH_URL_STRING = "http://testurl";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String TOKEN = "token";
    private static final String APP_KEY = "appKey";
    @Mock
    private RestTemplate restTemplate;
    private AuthClient authClient;

    @BeforeEach
    void setUp() throws URISyntaxException {
        openMocks(this);
        authClient = new AuthClient(restTemplate, getTestURI(), USERNAME, PASSWORD);
    }

    @Test
    void login_Success_SendsRequestAndReturnsResponse() throws URISyntaxException{
        AuthResponse expectedResponse = AuthResponse.builder()
                .token(TOKEN)
                .product(APP_KEY)
                .status(AuthStatus.SUCCESS)
                .build();

        when(restTemplate.postForObject(eq(loginURI()), any(), eq(AuthResponse.class)))
                .thenReturn(expectedResponse);

        Optional<AuthResponse> response = authClient.login();

        verify(restTemplate).postForObject(eq(loginURI()), any(), eq(AuthResponse.class));
        assertTrue(response.isPresent(), "Expected response to be present");
        assertEquals(expectedResponse, response.get());
    }

    @ParameterizedTest
    @MethodSource(value = "exceptionSource")
    void login_FailWithException_ReturnsEmptyOptional(Exception e) throws URISyntaxException {
        when(restTemplate.postForObject(eq(loginURI()), any(), eq(AuthResponse.class)))
                .thenThrow(e);

        var response = authClient.login();

        assertTrue(response.isEmpty(), "Expected response to be empty");
        verify(restTemplate).postForObject(eq(loginURI()), any(), eq(AuthResponse.class));
    }

    @Test
    void login_AuthError_ReturnsEmptyOptional() throws URISyntaxException {
        when(restTemplate.postForObject(eq(loginURI()), any(), eq(AuthResponse.class)))
                .thenReturn(AuthResponse.builder()
                        .error(AuthErrorCode.PENDING_AUTH)
                        .build());

        var response = authClient.login();

        assertTrue(response.isEmpty(), "Expected response to be empty");
        verify(restTemplate).postForObject(eq(loginURI()), any(), eq(AuthResponse.class));
    }

    @Test
    void keepAlive() throws URISyntaxException {
        var expectedResponse = AuthResponse.builder()
                .token(TOKEN)
                .product(APP_KEY)
                .status(AuthStatus.SUCCESS)
                .build();

        var expectedHeaders = new HttpHeaders();
        expectedHeaders.add(Headers.X_AUTHENTICATION.header(), TOKEN);

        when(restTemplate.postForObject(keepAliveURI(), expectedHeaders, AuthResponse.class))
                .thenReturn(AuthResponse.builder()
                        .token(TOKEN)
                        .product(APP_KEY)
                        .status(AuthStatus.SUCCESS)
                        .build());

        var response = authClient.keepAlive(TOKEN);
        assertTrue(response.isPresent(), "Expected response to be present");

        assertEquals(expectedResponse, response.get());
        verify(restTemplate).postForObject(keepAliveURI(), expectedHeaders, AuthResponse.class);

    }

    private static List<Exception> exceptionSource() {
        return List.of(
                new RuntimeException("Test RunTime Exception"),
                new RestClientResponseException("Test RestClient Exception",
                        404, "Not Found", null, null, null)
        );
    }

    private URI getTestURI() throws URISyntaxException {
        return new URI(AUTH_URL_STRING);
    }

    private URI loginURI() throws URISyntaxException {
        return UriComponentsBuilder.fromUri(getTestURI().resolve(LOGIN.path()))
                .queryParam(USERNAME, USERNAME)
                .queryParam(PASSWORD, PASSWORD)
                .build()
                .toUri();
    }

    private URI keepAliveURI() throws URISyntaxException {
        return UriComponentsBuilder
                .fromUri(getTestURI().resolve(KEEP_ALIVE.path()))
                .build()
                .toUri();
    }
}

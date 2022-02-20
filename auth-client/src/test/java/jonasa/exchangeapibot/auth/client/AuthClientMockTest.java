package jonasa.exchangeapibot.auth.client;

import jonasa.exchangeapibot.auth.enums.AuthStatus;
import jonasa.exchangeapibot.auth.types.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static jonasa.exchangeapibot.auth.util.AuthOperations.LOGIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AuthClientMockTest {
    private static final String AUTH_URL_STRING = "http://testurl";
    private static final String APP_KEY = "appKey";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
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
                .token("token")
                .product("appKey")
                .status(AuthStatus.SUCCESS)
                .build();

        when(restTemplate.postForObject(eq(expectedURI()), any(), eq(AuthResponse.class)))
                .thenReturn(expectedResponse);

        Optional<AuthResponse> response = authClient.login();

        verify(restTemplate).postForObject(eq(expectedURI()), any(), eq(AuthResponse.class));
        assertTrue(response.isPresent(), "Expected a response but none was present");
        assertEquals(expectedResponse, response.get());
    }

    @Test
    void login_Fail_ReturnsEmptyOptional() throws URISyntaxException {
        when(restTemplate.postForObject(eq(expectedURI()), any(), eq(AuthResponse.class)))
                .thenThrow(new RestClientResponseException(
                        "Test Exception - Should have been caught", 404, "Not Found", null, null, null));

        Optional<AuthResponse> response = authClient.login();

        verify(restTemplate).postForObject(eq(expectedURI()), any(), eq(AuthResponse.class));
        assertTrue(response.isEmpty(), "Expected no response but one was present");

        when(restTemplate.postForObject(eq(expectedURI()), any(), eq(AuthResponse.class)))
                .thenThrow(new RuntimeException("Test Exception - Should have been caught"));

        response = authClient.login();

        verify(restTemplate, times(2)).postForObject(eq(expectedURI()), any(), eq(AuthResponse.class));
        assertTrue(response.isEmpty(), "Expected no response but one was present");
    }

    @Test
    void setPort() {
        assertThrows(IllegalArgumentException.class, () -> authClient.setPort(-1));
        assertThrows(IllegalArgumentException.class, () -> authClient.setPort(65536));
    }

    private URI getTestURI() throws URISyntaxException {
        return new URI(AUTH_URL_STRING);
    }

    private URI expectedURI() throws URISyntaxException {
        return UriComponentsBuilder.fromUri(getTestURI().resolve(LOGIN.path()))
                .queryParam(USERNAME, USERNAME)
                .queryParam(PASSWORD, PASSWORD)
                .build()
                .toUri();
    }
}

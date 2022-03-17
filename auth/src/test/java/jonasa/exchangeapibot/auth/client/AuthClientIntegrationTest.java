package jonasa.exchangeapibot.auth.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import jonasa.exchangeapibot.auth.enums.AuthStatus;
import jonasa.exchangeapibot.auth.types.AuthResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static jonasa.exchangeapibot.auth.util.AuthOperations.KEEP_ALIVE;
import static jonasa.exchangeapibot.auth.util.AuthOperations.LOGIN;
import static jonasa.exchangeapibot.auth.util.AuthOperations.LOGOUT;
import static jonasa.exchangeapibot.auth.util.Headers.ACCEPT;
import static jonasa.exchangeapibot.auth.util.Headers.CONTENT_TYPE;
import static jonasa.exchangeapibot.auth.util.Headers.X_APPLICATION;
import static jonasa.exchangeapibot.auth.util.Headers.X_AUTHENTICATION;
import static jonasa.exchangeapibot.auth.util.Headers.X_IP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthClientIntegrationTest {
    private static final String ROOT_URI = "http://localhost/api";
    private static final int PORT = 80;

    private static final String BASE_PATH = "/api";
    private static final String LOGOUT_PATH = BASE_PATH + LOGOUT.path();
    private static final String KEEP_ALIVE_PATH = BASE_PATH + KEEP_ALIVE.path();
    private static final String LOGIN_PATH = BASE_PATH + LOGIN.path();
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String APP_KEY = "appKey";
    private static final String TOKEN = "token";
    private static final long TIMEOUT = 10;

    private static WireMockServer wireMockServer;
    private AuthClient authClient;
    private ObjectMapper mapper;

    @BeforeAll
    static void setUpAll() {
        wireMockServer = new WireMockServer(PORT);
        wireMockServer.start();
    }

    @BeforeEach
    void setUp() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(ROOT_URI)
                .defaultHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
                .defaultHeader(ACCEPT.header(), ACCEPT.value())
                .defaultHeader(X_IP.header(), X_IP.value())
                .defaultHeader(X_APPLICATION.header(), APP_KEY)
                .basicAuthentication(USERNAME, PASSWORD)
                .setConnectTimeout(Duration.ofSeconds(TIMEOUT))
                .setReadTimeout(Duration.ofSeconds(TIMEOUT))
                .build();

        var loginQuery = UriComponentsBuilder
                .fromUriString(LOGIN.path())
                .queryParam(USERNAME, USERNAME)
                .queryParam(PASSWORD, PASSWORD)
                .toUriString();

        authClient = new AuthClient(restTemplate, loginQuery);
        mapper = new ObjectMapper();
    }

    @AfterAll
    static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void login() throws JsonProcessingException {
        AuthResponse expectedResponse = AuthResponse.builder()
                .token(TOKEN)
                .product(APP_KEY)
                .status(AuthStatus.SUCCESS)
                .build();

        wireMockServer.stubFor(post(urlPathEqualTo(LOGIN_PATH))
                .withQueryParam(USERNAME, equalTo(USERNAME))
                .withQueryParam(PASSWORD, equalTo(PASSWORD))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
                        .withBody(mapper.writeValueAsString(expectedResponse))));

        Optional<AuthResponse> response = authClient.login();

        assertTrue(response.isPresent(), "Expected response to be present");
        assertEquals(expectedResponse, response.get());

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(LOGIN_PATH))
                .withHeader(CONTENT_TYPE.header(), equalTo(CONTENT_TYPE.value()))
                .withHeader(ACCEPT.header(), containing(ACCEPT.value()))
                .withHeader(X_IP.header(), equalTo(X_IP.value()))
                .withHeader(X_APPLICATION.header(), equalTo(APP_KEY))
                .withQueryParam(USERNAME, equalTo(USERNAME))
                .withQueryParam(PASSWORD, equalTo(PASSWORD)));
    }

    @Test
    void logout() throws JsonProcessingException {
        AuthResponse expectedResponse = AuthResponse.builder()
                .token(TOKEN)
                .product(APP_KEY)
                .status(AuthStatus.SUCCESS)
                .build();

        wireMockServer.stubFor(post(LOGOUT_PATH).willReturn(aResponse()
                .withStatus(200)
                .withHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
                .withBody(mapper.writeValueAsString(expectedResponse))));

        Optional<AuthResponse> response = authClient.logout(TOKEN);

        assertTrue(response.isPresent(), "Expected response to be present");
        assertEquals(expectedResponse, response.get());

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(LOGOUT_PATH))
                .withHeader(CONTENT_TYPE.header(), equalTo(CONTENT_TYPE.value()))
                .withHeader(ACCEPT.header(), containing(ACCEPT.value()))
                .withHeader(X_IP.header(), equalTo(X_IP.value()))
                .withHeader(X_APPLICATION.header(), equalTo(APP_KEY))
                .withHeader(X_AUTHENTICATION.header(), equalTo(TOKEN)));
    }

    @Test
    void keepAlive() throws JsonProcessingException {
        AuthResponse expectedResponse = AuthResponse.builder()
                .token(TOKEN)
                .product(APP_KEY)
                .status(AuthStatus.SUCCESS)
                .build();

        wireMockServer.stubFor(post(KEEP_ALIVE_PATH).willReturn(aResponse()
                .withStatus(200)
                .withHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
                .withBody(mapper.writeValueAsString(expectedResponse))));

        Optional<AuthResponse> response = authClient.keepAlive(TOKEN);

        assertTrue(response.isPresent(), "Expected response to be present");
        assertEquals(expectedResponse, response.get());

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(KEEP_ALIVE_PATH))
                .withHeader(CONTENT_TYPE.header(), equalTo(CONTENT_TYPE.value()))
                .withHeader(ACCEPT.header(), containing(ACCEPT.value()))
                .withHeader(X_IP.header(), equalTo(X_IP.value()))
                .withHeader(X_APPLICATION.header(), equalTo(APP_KEY))
                .withHeader(X_AUTHENTICATION.header(), equalTo(TOKEN)));
    }
}

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

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static jonasa.exchangeapibot.auth.util.AuthOperations.LOGIN;
import static jonasa.exchangeapibot.auth.util.Headers.ACCEPT;
import static jonasa.exchangeapibot.auth.util.Headers.CONTENT_TYPE;
import static jonasa.exchangeapibot.auth.util.Headers.X_APPLICATION;
import static jonasa.exchangeapibot.auth.util.Headers.X_IP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthClientIntegrationTest {
    private static final String AUTH_URL_STRING = "http://localhost";
    private static final int PORT = 80;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String APP_KEY = "appKey";
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
    void setUp() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .defaultHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
                .defaultHeader(ACCEPT.header(), ACCEPT.value())
                .defaultHeader(X_IP.header(), X_IP.value())
                .defaultHeader(X_APPLICATION.header(), APP_KEY)
                .basicAuthentication(USERNAME, PASSWORD)
                .setConnectTimeout(Duration.ofSeconds(TIMEOUT))
                .setReadTimeout(Duration.ofSeconds(TIMEOUT))
                .build();

        authClient = new AuthClient(restTemplate, getTestURI(), USERNAME, PASSWORD);
        mapper = new ObjectMapper();
    }

    @AfterAll
    static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void login_Success_SendsRequestWithCorrectHeaders() throws JsonProcessingException {
        AuthResponse expectedResponse = AuthResponse.builder()
                .token("token")
                .product("appKey")
                .status(AuthStatus.SUCCESS)
                .build();

        wireMockServer.stubFor(post(anyUrl())
                .withQueryParam(USERNAME, equalTo(USERNAME))
                .withQueryParam(PASSWORD, equalTo(PASSWORD))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
                        .withBody(mapper.writeValueAsString(expectedResponse))));

        Optional<AuthResponse> response = authClient.login();

        assertTrue(response.isPresent(), "Expected a response but none was present");
        assertEquals(expectedResponse, response.get());

        wireMockServer.verify(postRequestedFor(urlPathEqualTo(LOGIN.path()))
                .withHeader(CONTENT_TYPE.header(), equalTo(CONTENT_TYPE.value()))
                .withHeader(ACCEPT.header(), containing(ACCEPT.value()))
                .withHeader(X_IP.header(), equalTo(X_IP.value()))
                .withHeader(X_APPLICATION.header(), equalTo(APP_KEY))
                .withQueryParam(USERNAME, equalTo(USERNAME))
                .withQueryParam(PASSWORD, equalTo(PASSWORD))
        );
    }

    private URI getTestURI() throws URISyntaxException {
        return new URI(AUTH_URL_STRING);
    }
}

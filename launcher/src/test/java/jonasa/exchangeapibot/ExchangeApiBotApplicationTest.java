package jonasa.exchangeapibot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import jonasa.exchangeapibot.auth.types.AuthResponse;
import jonasa.exchangeapibot.config.TestApplicationRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static jonasa.exchangeapibot.auth.util.AuthOperations.LOGIN;
import static jonasa.exchangeapibot.util.Headers.CONTENT_TYPE;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestApplicationRunner.class)
class ExchangeApiBotApplicationTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final int PORT = 80;
    private static final WireMockServer SERVER = new WireMockServer(PORT);

    @BeforeAll
    static void setUp() throws JsonProcessingException {
        var authResponse = AuthResponse.builder()
                .token("token")
                .build();

        SERVER.stubFor(post(urlPathEqualTo(LOGIN.path()))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
                        .withBody(MAPPER.writeValueAsString(authResponse))));

        SERVER.start();
    }

    @AfterAll
    static void tearDown() {
        SERVER.stop();
    }

    @Test
    void contextLoads() {
    }
}

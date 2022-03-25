package io.github.aj8gh.bot.betting.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.aj8gh.bot.domain.betting.types.EventType;
import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static io.github.aj8gh.bot.betting.util.BettingOperations.LIST_EVENT_TYPES;
import static io.github.aj8gh.bot.domain.util.Headers.ACCEPT;
import static io.github.aj8gh.bot.domain.util.Headers.CONTENT_TYPE;
import static io.github.aj8gh.bot.domain.util.Headers.X_APPLICATION;
import static io.github.aj8gh.bot.domain.util.Headers.X_AUTHENTICATION;
import static io.github.aj8gh.bot.domain.util.Headers.X_IP;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@Slf4j
class BettingClientIntegrationTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final int PORT = 3000;
    private static final String HOST = "http://localhost:3000";
    private static final String BASE_PATH = "/exchange/betting/rest/v1.0";
    private static final String ROOT_URI = HOST + BASE_PATH;
    private static final String APP_KEY = "appKey";
    private static final String SESSION_TOKEN = "sessionToken";


    private static WireMockServer wireMockServer;
    private BettingClient bettingClient;

    @BeforeAll
    static void setUpAll() {
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        wireMockServer = new WireMockServer(PORT);
        wireMockServer.start();
    }

    @BeforeEach
    void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        var converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper);

        RestTemplate restTemplate = new RestTemplateBuilder()
                .additionalMessageConverters(converter)
                .rootUri(ROOT_URI)
                .defaultHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
                .defaultHeader(ACCEPT.header(), ACCEPT.value())
                .defaultHeader(X_IP.header(), X_IP.value())
                .defaultHeader(X_APPLICATION.header(), APP_KEY)
                .defaultHeader(X_AUTHENTICATION.header(), SESSION_TOKEN)
                .build();

        bettingClient = new BettingClient(restTemplate);
    }

    @AfterAll
    static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void listEventTypes() throws JsonProcessingException {
        var requestBody = MAPPER.writeValueAsString(Map.of("filter", new MarketFilter()));
        var listEventTypesPath = BASE_PATH + LIST_EVENT_TYPES.path();
        var expectedResponse = new EventTypeResult[] {
                EventTypeResult.builder()
                        .marketCount(99)
                        .eventType(EventType.builder()
                                .name("Football")
                                .build())
                        .build()
        };

        wireMockServer.stubFor(post(urlPathEqualTo(listEventTypesPath))
                .withRequestBody(equalToJson(requestBody))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
                        .withBody(MAPPER.writeValueAsString(expectedResponse))));

        var response = bettingClient.listEventTypes(MarketFilter.builder().build());
        verifyRequest(listEventTypesPath, requestBody);
        assertArrayEquals(expectedResponse, response.toArray());
    }

    private void verifyRequest(String path, String requestBody) {
        wireMockServer.verify(postRequestedFor(urlPathEqualTo(path))
                .withHeader(CONTENT_TYPE.header(), equalTo(CONTENT_TYPE.value()))
                .withHeader(ACCEPT.header(), containing(ACCEPT.value()))
                .withHeader(X_IP.header(), equalTo(X_IP.value()))
                .withHeader(X_APPLICATION.header(), equalTo(APP_KEY))
                .withHeader(X_AUTHENTICATION.header(), equalTo(SESSION_TOKEN))
                .withRequestBody(equalToJson(requestBody)));
    }
}

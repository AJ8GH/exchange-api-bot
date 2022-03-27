package io.github.aj8gh.bot.http.client;

import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_EVENT_TYPES;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class HttpClientTest {
    private HttpClient httpClient;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        openMocks(this);
        httpClient = new HttpClient(restTemplate);
    }

    @Test
    void listEventTypes_Success_ReturnsListOfResults() {
        var requestBody = MarketFilter.builder().build();
        var request = new HttpEntity<>(Map.of("filter", requestBody));
        var expectedResponse = new EventTypeResult[] { new EventTypeResult() };

        when(restTemplate.postForObject(
                LIST_EVENT_TYPES.path(), request, EventTypeResult[].class))
                .thenReturn(expectedResponse);

        var response = httpClient.post(
                LIST_EVENT_TYPES.path(), request, EventTypeResult[].class);

        verify(restTemplate).postForObject(
                LIST_EVENT_TYPES.path(), request, EventTypeResult[].class);
        assertTrue(response.isPresent());
        assertEquals(expectedResponse, response.get());
    }

    @ParameterizedTest
    @MethodSource(value = "exceptionProvider")
    void listEventTypes_ExceptionThrown_ReturnsEmptyList(Exception exception) {
        var requestBody = MarketFilter.builder().build();
        var request = new HttpEntity<>(Map.of("filter", requestBody));

        when(restTemplate.postForObject(LIST_EVENT_TYPES.path(), request, EventTypeResult[].class))
                .thenThrow(exception);

        var response = httpClient.post(
                LIST_EVENT_TYPES.path(), request, EventTypeResult[].class);
        assertTrue(response.isEmpty());
    }

    private static List<Exception> exceptionProvider() {
        return List.of(
                new RuntimeException("Test RuntimeException"),
                new RestClientResponseException(
                        "Test RestException", 500, "Error", null, null, null)
        );
    }
}

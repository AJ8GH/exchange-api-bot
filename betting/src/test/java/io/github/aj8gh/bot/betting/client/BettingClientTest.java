package io.github.aj8gh.bot.betting.client;

import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import io.github.aj8gh.bot.http.client.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;

import java.util.Map;
import java.util.Optional;

import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_EVENT_TYPES;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class BettingClientTest {

    private BettingClient bettingClient;
    @Mock
    private HttpClient httpClient;

    @BeforeEach
    void setUp() {
        openMocks(this);
        bettingClient = new BettingClient(httpClient);
    }

    @Test
    void listEventTypes_Success_ReturnsListOfResults() {
        var requestBody = MarketFilter.builder().build();
        var request = new HttpEntity<>(Map.of("filter", requestBody));
        var expectedResponse = new EventTypeResult[] { new EventTypeResult() };

        when(httpClient.post(
                LIST_EVENT_TYPES.path(), request, EventTypeResult[].class))
                .thenReturn(Optional.of(expectedResponse));

        var response = bettingClient.listEventTypes(requestBody);
        verify(httpClient).post(
                LIST_EVENT_TYPES.path(), request, EventTypeResult[].class);
        assertArrayEquals(expectedResponse, response.toArray());
    }
}

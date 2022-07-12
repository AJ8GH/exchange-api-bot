package io.github.aj8gh.bot.betting;

import io.github.aj8gh.bot.betting.cache.BettingCache;
import io.github.aj8gh.bot.betting.client.BettingClient;
import io.github.aj8gh.bot.betting.client.RequestBuilder;
import io.github.aj8gh.bot.domain.betting.requests.MarketFilterRequest;
import io.github.aj8gh.bot.domain.betting.types.EventType;
import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class BettingApiTest {
    private static final MarketFilterRequest MARKET_FILTER_REQUEST = MarketFilterRequest
            .builder().filter(MarketFilter.builder().build()).build();

    private BettingApi bettingApi;
    @Mock private BettingCache bettingCache;
    @Mock private BettingClient bettingClient;
    @Mock private RequestBuilder requestBuilder;

    @BeforeEach
    void setUp() {
        openMocks(this);
        when(requestBuilder.getMarketFilterRequest())
                .thenReturn(MARKET_FILTER_REQUEST);

        bettingApi = new BettingApi(bettingClient, bettingCache, requestBuilder);
    }

    @Test
    void listEventTypes() {
        var expected = List.of(EventTypeResult.builder().build());
        when(bettingClient.listEventTypes(MARKET_FILTER_REQUEST)).thenReturn(expected);
        var actual = bettingApi.listEventTypes();

        verify(requestBuilder).getMarketFilterRequest();
        verify(bettingClient).listEventTypes(MARKET_FILTER_REQUEST);
        assertEquals(expected, actual);
    }
}

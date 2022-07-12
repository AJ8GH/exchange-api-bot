package io.github.aj8gh.bot.betting.cache;

import io.github.aj8gh.bot.betting.client.BettingClient;
import io.github.aj8gh.bot.domain.betting.requests.MarketCatalogueRequest;
import io.github.aj8gh.bot.domain.betting.types.MarketCatalogue;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import io.github.aj8gh.bot.domain.betting.types.RunnerCatalog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class BettingCacheTest {
    private static final String MARKET_ID = "123";
    private static final MarketCatalogueRequest MARKET_CATALOGUE_REQUEST = MarketCatalogueRequest
            .builder().filter(MarketFilter.builder().marketIds(Set.of(MARKET_ID)).build()).build();

    @Mock
    private BettingClient bettingClient;
    private BettingCache bettingCache;
    private Map<String, MarketCatalogue> cache;

    @BeforeEach
    void setUp() {
        openMocks(this);
        cache = new HashMap<>();
        bettingCache = new BettingCache(bettingClient,  cache);
    }

    @Test
    void getRunnerCatalogue_CatalogueNotInCache_CallsClient() {
        var expectedRunners = List.of(RunnerCatalog.builder()
                .selectionId(123L)
                .handicap(1.0)
                .sortPriority(3)
                .runnerName("runnerName").build());
        var marketCatalogue = MarketCatalogue.builder()
                .marketId(MARKET_ID).marketName("marketName")
                .runners(expectedRunners).build();
        when(bettingClient.listMarketCatalogue(MARKET_CATALOGUE_REQUEST))
                .thenReturn(List.of(marketCatalogue));

        var actualRunners = bettingCache.getRunnerCatalogue(MARKET_ID);

        verify(bettingClient).listMarketCatalogue(MARKET_CATALOGUE_REQUEST);
        assertEquals(expectedRunners, actualRunners);
    }

    @Test
    void getRunnerCatalogue_CatalogueFoundInCache_NoCallToClient() {
        var expectedRunners = List.of(RunnerCatalog.builder()
                .selectionId(123L)
                .handicap(1.0)
                .sortPriority(3)
                .runnerName("runnerName").build());
        var marketCatalogue = MarketCatalogue.builder()
                .marketId(MARKET_ID).marketName("marketName")
                .runners(expectedRunners).build();
        cache.put(MARKET_ID, marketCatalogue);

        var actualRunners = bettingCache.getRunnerCatalogue(MARKET_ID);

        verifyNoInteractions(bettingClient);
        assertEquals(expectedRunners, actualRunners);
    }

    @Test
    void getRunnerCatalogue_EmptyListFromClient_ReturnsEmptyList() {
        when(bettingClient.listMarketCatalogue(MARKET_CATALOGUE_REQUEST))
                .thenReturn(emptyList());

        var actual = bettingCache.getRunnerCatalogue(MARKET_ID);
        assertEquals(emptyList(), actual);
    }
}

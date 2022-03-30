package io.github.aj8gh.bot.betting.client;

import io.github.aj8gh.bot.domain.betting.types.CompetitionResult;
import io.github.aj8gh.bot.domain.betting.types.CountryCodeResult;
import io.github.aj8gh.bot.domain.betting.types.EventResult;
import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import io.github.aj8gh.bot.domain.betting.types.MarketTypeResult;
import io.github.aj8gh.bot.domain.betting.types.VenueResult;
import io.github.aj8gh.bot.http.client.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_COMPETITIONS;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_COUNTRIES;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_EVENTS;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_EVENT_TYPES;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_MARKET_TYPES;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_VENUES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class BettingClientTest {
    private static final MarketFilter MARKET_FILTER = MarketFilter.builder().build();
    private static final HttpEntity<Map<String, MarketFilter>> REQUEST = new HttpEntity<>(
            Map.of("filter", MARKET_FILTER));

    private BettingClient bettingClient;
    @Mock
    private HttpClient httpClient;

    @BeforeEach
    void setUp() {
        openMocks(this);
        bettingClient = new BettingClient(httpClient);
    }

    @Test
    void listEventTypes() {
        var expected = new EventTypeResult[] { new EventTypeResult() };
        var path = LIST_EVENT_TYPES.path();
        var type = EventTypeResult[].class;
        stubRequest(path, type, expected);

        var actual = bettingClient.listEventTypes(MARKET_FILTER);
        verifyRequest(path, type, expected, actual);
    }

    @Test
    void listCompetitions() {
        var expected = new CompetitionResult[] { new CompetitionResult() };
        var path = LIST_COMPETITIONS.path();
        var type = CompetitionResult[].class;
        stubRequest(path, type, expected);

        var actual = bettingClient.listCompetitions(MARKET_FILTER);
        verifyRequest(path, type, expected, actual);
    }

    @Test
    void listEvents() {
        var expected = new EventResult[] { new EventResult() };
        var path = LIST_EVENTS.path();
        var type = EventResult[].class;
        stubRequest(path, type, expected);

        var actual = bettingClient.listEvents(MARKET_FILTER);
        verifyRequest(path, type, expected, actual);
    }

    @Test
    void listMarketTypes() {
        var expected = new MarketTypeResult[] { new MarketTypeResult() };
        var path = LIST_MARKET_TYPES.path();
        var type = MarketTypeResult[].class;
        stubRequest(path, type, expected);

        var actual = bettingClient.listMarketTypes(MARKET_FILTER);
        verifyRequest(path, type, expected, actual);
    }

    @Test
    void listCountries() {
        var expected = new CountryCodeResult[] { new CountryCodeResult() };
        var path = LIST_COUNTRIES.path();
        var type = CountryCodeResult[].class;
        stubRequest(path, type, expected);

        var actual = bettingClient.listCountries(MARKET_FILTER);
        verifyRequest(path, type, expected, actual);
    }

    @Test
    void listVenues() {
        var expected = new VenueResult[] { new VenueResult() };
        var path = LIST_VENUES.path();
        var type = VenueResult[].class;
        stubRequest(path, type, expected);

        var actual = bettingClient.listVenues(MARKET_FILTER);
        verifyRequest(path, type, expected, actual);
    }

    private <T> void stubRequest(String path, Class<T[]> type, T[] response) {
        when(httpClient.post(path, REQUEST, type))
                .thenReturn(Optional.ofNullable(response));
    }

    private <T> void verifyRequest(String path, Class<T[]> type,
                                   T[] expected, List<T> actual) {
        verify(httpClient).post(path, REQUEST, type);
        assertEquals(Arrays.asList(expected), actual);
    }
}

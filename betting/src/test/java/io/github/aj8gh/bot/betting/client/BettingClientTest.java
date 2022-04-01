package io.github.aj8gh.bot.betting.client;

import io.github.aj8gh.bot.domain.betting.enums.TimeGranularity;
import io.github.aj8gh.bot.domain.betting.requests.MarketBookRequest;
import io.github.aj8gh.bot.domain.betting.requests.MarketCatalogueRequest;
import io.github.aj8gh.bot.domain.betting.requests.MarketFilterRequest;
import io.github.aj8gh.bot.domain.betting.requests.MarketProfitAndLossRequest;
import io.github.aj8gh.bot.domain.betting.requests.RunnerBookRequest;
import io.github.aj8gh.bot.domain.betting.requests.TimeRangeRequest;
import io.github.aj8gh.bot.domain.betting.types.CompetitionResult;
import io.github.aj8gh.bot.domain.betting.types.CountryCodeResult;
import io.github.aj8gh.bot.domain.betting.types.EventResult;
import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketBook;
import io.github.aj8gh.bot.domain.betting.types.MarketCatalogue;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import io.github.aj8gh.bot.domain.betting.types.MarketProfitAndLoss;
import io.github.aj8gh.bot.domain.betting.types.MarketTypeResult;
import io.github.aj8gh.bot.domain.betting.types.TimeRangeResult;
import io.github.aj8gh.bot.domain.betting.types.VenueResult;
import io.github.aj8gh.bot.http.client.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_COMPETITIONS;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_COUNTRIES;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_EVENTS;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_EVENT_TYPES;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_MARKET_BOOK;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_MARKET_CATALOGUE;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_MARKET_PROFIT_AND_LOSS;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_MARKET_TYPES;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_RUNNER_BOOK;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_TIME_RANGES;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_VENUES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class BettingClientTest {
    private static final MarketFilter MARKET_FILTER = MarketFilter.builder().build();
    private static final MarketFilterRequest MARKET_FILTER_REQUEST = MarketFilterRequest.builder()
            .filter(MARKET_FILTER).build();
    private static final MarketCatalogueRequest MARKET_CATALOGUE_REQUEST = MarketCatalogueRequest.builder()
            .filter(MARKET_FILTER).build();
    private static final MarketBookRequest MARKET_BOOK_REQUEST = MarketBookRequest.builder()
            .marketIds(List.of("123")).build();
    private static final RunnerBookRequest RUNNER_BOOK_REQUEST = RunnerBookRequest.builder()
            .marketId("123").selectionId(123L).build();
    private static final MarketProfitAndLossRequest MARKET_PROFIT_AND_LOSS_REQUEST = MarketProfitAndLossRequest.builder()
            .marketIds(Set.of("123")).build();
    private static final HttpEntity<MarketFilterRequest> MARKET_FILTER_REQUEST_ENTITY =
            new HttpEntity<>(MARKET_FILTER_REQUEST);
    private static final TimeRangeRequest TIME_RANGES_REQUEST = TimeRangeRequest.builder()
            .filter(MARKET_FILTER).granularity(TimeGranularity.DAYS).build();

    @Mock
    private HttpClient httpClient;
    private BettingClient bettingClient;

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
        var request = MARKET_FILTER_REQUEST_ENTITY;
        stubRequest(path, type, request, expected);

        var actual = bettingClient.listEventTypes(MARKET_FILTER_REQUEST);
        verifyRequest(path, type, request, expected, actual);
    }

    @Test
    void listCompetitions() {
        var expected = new CompetitionResult[] { new CompetitionResult() };
        var path = LIST_COMPETITIONS.path();
        var type = CompetitionResult[].class;
        var request = MARKET_FILTER_REQUEST_ENTITY;
        stubRequest(path, type, request, expected);

        var actual = bettingClient.listCompetitions(MARKET_FILTER_REQUEST);
        verifyRequest(path, type, request, expected, actual);
    }

    @Test
    void listEvents() {
        var expected = new EventResult[] { new EventResult() };
        var path = LIST_EVENTS.path();
        var type = EventResult[].class;
        var request = MARKET_FILTER_REQUEST_ENTITY;
        stubRequest(path, type, request, expected);

        var actual = bettingClient.listEvents(MARKET_FILTER_REQUEST);
        verifyRequest(path, type, request, expected, actual);
    }

    @Test
    void listMarketTypes() {
        var expected = new MarketTypeResult[] { new MarketTypeResult() };
        var path = LIST_MARKET_TYPES.path();
        var type = MarketTypeResult[].class;
        var request = MARKET_FILTER_REQUEST_ENTITY;
        stubRequest(path, type, request, expected);

        var actual = bettingClient.listMarketTypes(MARKET_FILTER_REQUEST);
        verifyRequest(path, type, request, expected, actual);
    }

    @Test
    void listCountries() {
        var expected = new CountryCodeResult[] { new CountryCodeResult() };
        var path = LIST_COUNTRIES.path();
        var type = CountryCodeResult[].class;
        var request = MARKET_FILTER_REQUEST_ENTITY;
        stubRequest(path, type, request, expected);

        var actual = bettingClient.listCountries(MARKET_FILTER_REQUEST);
        verifyRequest(path, type, request, expected, actual);
    }

    @Test
    void listVenues() {
        var expected = new VenueResult[] { new VenueResult() };
        var path = LIST_VENUES.path();
        var type = VenueResult[].class;
        var request = MARKET_FILTER_REQUEST_ENTITY;
        stubRequest(path, type, request, expected);

        var actual = bettingClient.listVenues(MARKET_FILTER_REQUEST);
        verifyRequest(path, type, request, expected, actual);
    }

    @Test
    void listTimeRanges() {
        var expected = new TimeRangeResult[] { new TimeRangeResult() };
        var path = LIST_TIME_RANGES.path();
        var type = TimeRangeResult[].class;
        var request = new HttpEntity<>(TIME_RANGES_REQUEST);
        stubRequest(path, type, request, expected);

        var actual = bettingClient.listTimeRanges(TIME_RANGES_REQUEST);
        verifyRequest(path, type, request, expected, actual);
    }

    @Test
    void listMarketCatalogue() {
        var expected = new MarketCatalogue[] { MarketCatalogue.builder()
                .marketId("123").marketName("market").build() };
        var path = LIST_MARKET_CATALOGUE.path();
        var type = MarketCatalogue[].class;
        var request = new HttpEntity<>(MARKET_CATALOGUE_REQUEST);
        stubRequest(path, type, request, expected);

        var actual = bettingClient.listMarketCatalogue(MARKET_CATALOGUE_REQUEST);
        verifyRequest(path, type, request, expected, actual);
    }

    @Test
    void listMarketBook() {
        var expected = new MarketBook[] { MarketBook.builder()
                .marketId("123").isMarketDataDelayed(true).build() };
        var path = LIST_MARKET_BOOK.path();
        var type = MarketBook[].class;
        var request = new HttpEntity<>(MARKET_BOOK_REQUEST);
        stubRequest(path, type, request, expected);

        var actual = bettingClient.listMarketBook(MARKET_BOOK_REQUEST);
        verifyRequest(path, type, request, expected, actual);
    }

    @Test
    void listRunnerBook() {
        var expected = new MarketBook[] { MarketBook.builder()
                .marketId("123").isMarketDataDelayed(true).build() };
        var path = LIST_RUNNER_BOOK.path();
        var type = MarketBook[].class;
        var request = new HttpEntity<>(RUNNER_BOOK_REQUEST);
        stubRequest(path, type, request, expected);

        var actual = bettingClient.listRunnerBook(RUNNER_BOOK_REQUEST);
        verifyRequest(path, type, request, expected, actual);
    }

    @Test
    void listMarketProfitAndLoss() {
        var expected = new MarketProfitAndLoss[] { MarketProfitAndLoss.builder().build() };
        var path = LIST_MARKET_PROFIT_AND_LOSS.path();
        var type = MarketProfitAndLoss[].class;
        var request = new HttpEntity<>(MARKET_PROFIT_AND_LOSS_REQUEST);
        stubRequest(path, type, request, expected);

        var actual = bettingClient.listMarketProfitAndLoss(MARKET_PROFIT_AND_LOSS_REQUEST);
        verifyRequest(path, type, request, expected, actual);
    }

    private <T> void stubRequest(String path, Class<T[]> type,
                                 HttpEntity<?> request, T[] response) {
        when(httpClient.post(path, request, type))
                .thenReturn(Optional.ofNullable(response));
    }

    private <T> void verifyRequest(String path, Class<T[]> type,
                                   HttpEntity<?> request, T[] expected,
                                   List<T> actual) {
        verify(httpClient).post(path, request, type);
        assertEquals(Arrays.asList(expected), actual);
    }
}

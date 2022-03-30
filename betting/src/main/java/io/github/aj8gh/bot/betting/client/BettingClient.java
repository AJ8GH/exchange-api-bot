package io.github.aj8gh.bot.betting.client;

import io.github.aj8gh.bot.domain.betting.types.CompetitionResult;
import io.github.aj8gh.bot.domain.betting.types.CountryCodeResult;
import io.github.aj8gh.bot.domain.betting.types.EventResult;
import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import io.github.aj8gh.bot.domain.betting.types.MarketTypeResult;
import io.github.aj8gh.bot.domain.betting.types.VenueResult;
import io.github.aj8gh.bot.http.client.HttpClient;
import org.springframework.http.HttpEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_COMPETITIONS;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_COUNTRIES;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_EVENTS;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_EVENT_TYPES;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_MARKET_TYPES;
import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_VENUES;
import static java.util.Collections.emptyList;

public class BettingClient {
    private final HttpClient httpClient;

    public BettingClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<EventTypeResult> listEventTypes(MarketFilter filter) {
        return requestCollection(LIST_EVENT_TYPES.path(),
                buildFilterRequest(filter),
                EventTypeResult[].class);
    }

    public List<EventResult> listEvents(MarketFilter filter) {
        return requestCollection(LIST_EVENTS.path(),
                buildFilterRequest(filter),
                EventResult[].class);
    }

    public List<CompetitionResult> listCompetitions(MarketFilter filter) {
        return requestCollection(LIST_COMPETITIONS.path(),
                buildFilterRequest(filter),
                CompetitionResult[].class);
    }

    public List<MarketTypeResult> listMarketTypes(MarketFilter filter) {
        return requestCollection(LIST_MARKET_TYPES.path(),
                buildFilterRequest(filter),
                MarketTypeResult[].class);
    }

    public List<CountryCodeResult> listCountries(MarketFilter filter) {
        return requestCollection(LIST_COUNTRIES.path(),
                buildFilterRequest(filter),
                CountryCodeResult[].class);
    }

    public List<VenueResult> listVenues(MarketFilter filter) {
        return requestCollection(LIST_VENUES.path(),
                buildFilterRequest(filter),
                VenueResult[].class);
    }

    private <T> List<T> requestCollection(String path,
                                          HttpEntity<?> request,
                                          Class<T[]> type) {
        return httpClient.post(path, request, type)
                .map(Arrays::asList)
                .orElse(emptyList());
    }

    private HttpEntity<Map<String, MarketFilter>> buildFilterRequest(MarketFilter filter) {
        return new HttpEntity<>(Map.of("filter", filter));
    }
}

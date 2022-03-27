package io.github.aj8gh.bot.betting.client;

import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import io.github.aj8gh.bot.http.client.HttpClient;
import org.springframework.http.HttpEntity;

import java.util.*;

import static io.github.aj8gh.bot.http.operations.BettingOperations.LIST_EVENT_TYPES;
import static java.util.Collections.emptyList;

public class BettingClient {
    private final HttpClient httpClient;

    public BettingClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<EventTypeResult> listEventTypes(MarketFilter filter) {
        return httpClient.sendRequest(
                LIST_EVENT_TYPES.path(),
                new HttpEntity<>(Map.of("filter", filter)),
                EventTypeResult[].class
        ).map(Arrays::asList).orElse(emptyList());
    }
}

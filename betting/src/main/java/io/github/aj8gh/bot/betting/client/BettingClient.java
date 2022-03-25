package io.github.aj8gh.bot.betting.client;

import io.github.aj8gh.bot.betting.util.BettingOperations;
import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.emptyList;

public class BettingClient {
    private static final Logger LOG = LoggerFactory.getLogger(BettingClient.class);

    private final RestTemplate restTemplate;

    public BettingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<EventTypeResult> listEventTypes(MarketFilter filter) {
        try {
            var request = new HttpEntity<>(Map.of("filter", filter));
            var response = restTemplate.postForObject(
                    BettingOperations.LIST_EVENT_TYPES.path(), request, EventTypeResult[].class);
            return Arrays.asList(Objects.requireNonNull(response));
        } catch (RestClientResponseException e) {
            LOG.error("{}: {}, {}", BettingOperations.LIST_EVENT_TYPES.path(),
                    e.getRawStatusCode(), e.getMessage());
        } catch (Exception e) {
            LOG.error("{}: {}", BettingOperations.LIST_EVENT_TYPES.path(), e.getMessage());
        }
        return emptyList();
    }
}

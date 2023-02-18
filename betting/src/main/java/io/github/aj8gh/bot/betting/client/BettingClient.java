package io.github.aj8gh.bot.betting.client;

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
import static java.util.Collections.emptyList;

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
import io.github.aj8gh.bot.domain.betting.types.MarketProfitAndLoss;
import io.github.aj8gh.bot.domain.betting.types.MarketTypeResult;
import io.github.aj8gh.bot.domain.betting.types.TimeRangeResult;
import io.github.aj8gh.bot.domain.betting.types.VenueResult;
import io.github.aj8gh.bot.http.client.HttpClient;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpEntity;

public class BettingClient {

  private final HttpClient httpClient;

  public BettingClient(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public List<EventTypeResult> listEventTypes(MarketFilterRequest request) {
    return requestCollection(LIST_EVENT_TYPES.path(),
        new HttpEntity<>(request),
        EventTypeResult[].class);
  }

  public List<EventResult> listEvents(MarketFilterRequest request) {
    return requestCollection(LIST_EVENTS.path(),
        new HttpEntity<>(request),
        EventResult[].class);
  }

  public List<CompetitionResult> listCompetitions(MarketFilterRequest request) {
    return requestCollection(LIST_COMPETITIONS.path(),
        new HttpEntity<>(request),
        CompetitionResult[].class);
  }

  public List<MarketTypeResult> listMarketTypes(MarketFilterRequest request) {
    return requestCollection(LIST_MARKET_TYPES.path(),
        new HttpEntity<>(request),
        MarketTypeResult[].class);
  }

  public List<CountryCodeResult> listCountries(MarketFilterRequest request) {
    return requestCollection(LIST_COUNTRIES.path(),
        new HttpEntity<>(request),
        CountryCodeResult[].class);
  }

  public List<VenueResult> listVenues(MarketFilterRequest request) {
    return requestCollection(LIST_VENUES.path(),
        new HttpEntity<>(request),
        VenueResult[].class);
  }

  public List<TimeRangeResult> listTimeRanges(TimeRangeRequest request) {
    return requestCollection(LIST_TIME_RANGES.path(),
        new HttpEntity<>(request),
        TimeRangeResult[].class);
  }

  public List<MarketCatalogue> listMarketCatalogue(MarketCatalogueRequest request) {
    return requestCollection(LIST_MARKET_CATALOGUE.path(),
        new HttpEntity<>(request),
        MarketCatalogue[].class);
  }

  public List<MarketBook> listMarketBook(MarketBookRequest request) {
    return requestCollection(LIST_MARKET_BOOK.path(),
        new HttpEntity<>(request),
        MarketBook[].class);
  }

  public List<MarketBook> listRunnerBook(RunnerBookRequest request) {
    return requestCollection(LIST_RUNNER_BOOK.path(),
        new HttpEntity<>(request),
        MarketBook[].class);
  }

  public List<MarketProfitAndLoss> listMarketProfitAndLoss(MarketProfitAndLossRequest request) {
    return requestCollection(LIST_MARKET_PROFIT_AND_LOSS.path(),
        new HttpEntity<>(request),
        MarketProfitAndLoss[].class);
  }

  private <T> List<T> requestCollection(String path, HttpEntity<?> request, Class<T[]> type) {
    return httpClient.post(path, request, type)
        .map(Arrays::asList)
        .orElse(emptyList());
  }
}

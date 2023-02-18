package io.github.aj8gh.bot.console;

import static io.github.aj8gh.bot.console.util.constants.Descriptions.MARKET_ID;
import static io.github.aj8gh.bot.console.util.constants.Descriptions.MARKET_PROJECTION;
import static io.github.aj8gh.bot.console.util.constants.Descriptions.MARKET_SORT;
import static io.github.aj8gh.bot.console.util.constants.Descriptions.MAX_RESULTS;
import static io.github.aj8gh.bot.console.util.constants.Descriptions.TIME_GRANULARITY;
import static org.springframework.shell.standard.ShellOption.NULL;

import io.github.aj8gh.bot.betting.client.BettingClient;
import io.github.aj8gh.bot.console.util.MarketCache;
import io.github.aj8gh.bot.console.util.RequestBuilder;
import io.github.aj8gh.bot.console.util.ShellPrinter;
import io.github.aj8gh.bot.console.util.constants.Descriptions;
import io.github.aj8gh.bot.console.util.constants.Messages;
import io.github.aj8gh.bot.domain.betting.enums.MarketProjection;
import io.github.aj8gh.bot.domain.betting.enums.MarketSort;
import io.github.aj8gh.bot.domain.betting.enums.TimeGranularity;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@Slf4j
@ShellComponent
public class BettingConsole {

  private final BettingClient bettingClient;
  private final RequestBuilder requestBuilder;
  private final MarketCache marketCache;
  private final ShellPrinter shell;

  public BettingConsole(BettingClient bettingClient,
                        RequestBuilder requestBuilder,
                        MarketCache marketCache,
                        ShellPrinter shell) {
    this.bettingClient = bettingClient;
    this.requestBuilder = requestBuilder;
    this.marketCache = marketCache;
    this.shell = shell;
  }

  @ShellMethod(key = {"list-event-types", "let"}, value = Descriptions.LIST_EVENT_TYPES)
  public void listEventTypes() {
    var request = requestBuilder.getMarketFilterRequest();
    printResult(bettingClient.listEventTypes(request));
  }

  @ShellMethod(key = {"list-events", "le"}, value = Descriptions.LIST_EVENTS)
  public void listEvents() {
    var request = requestBuilder.getMarketFilterRequest();
    printResult(bettingClient.listEvents(request));
  }

  @ShellMethod(key = {"list-competitions", "lc"}, value = Descriptions.LIST_COMPETITIONS)
  public void listCompetitions() {
    var request = requestBuilder.getMarketFilterRequest();
    printResult(bettingClient.listCompetitions(request));
  }

  @ShellMethod(key = {"list-market-types", "lmt"}, value = Descriptions.LIST_MARKET_TYPES)
  public void listMarketTypes() {
    var request = requestBuilder.getMarketFilterRequest();
    printResult(bettingClient.listMarketTypes(request));
  }

  @ShellMethod(key = {"list-countries", "lct"}, value = Descriptions.LIST_COUNTRIES)
  public void listCountries() {
    var request = requestBuilder.getMarketFilterRequest();
    printResult(bettingClient.listCountries(request));
  }

  @ShellMethod(key = {"list-venues", "lv"}, value = Descriptions.LIST_VENUES)
  public void listVenues() {
    var request = requestBuilder.getMarketFilterRequest();
    printResult(bettingClient.listVenues(request));
  }

  @ShellMethod(key = {"list-time-ranges", "ltr"}, value = Descriptions.LIST_TIME_RANGES)
  public void listTimeRanges(
      @ShellOption(defaultValue = NULL, value = {"-g", "--granularity"},
          help = TIME_GRANULARITY) final TimeGranularity granularity) {
    var request = requestBuilder.getTimeRangeRequest(granularity);
    printResult(bettingClient.listTimeRanges(request));
  }

  @ShellMethod(key = {"list-market-catalogue", "lmc"}, value = Descriptions.LIST_MARKET_CATALOGUE)
  public void listMarketCatalogue(
      @ShellOption(
          defaultValue = NULL, value = {"-mp", "--market-projection"}, help = MARKET_PROJECTION)
      final Set<MarketProjection> marketProjection,

      @ShellOption(
          defaultValue = NULL, value = {"-s", "--sort"}, help = MARKET_SORT) final MarketSort sort,

      @ShellOption(
          defaultValue = NULL, value = {"-mr", "--max-results"}, help = MAX_RESULTS)
      final Integer maxResults) {

    var request = requestBuilder.getMarketCatalogueRequest(marketProjection, sort, maxResults);
    var response = bettingClient.listMarketCatalogue(request);

    marketCache.cacheMarketCatalogue(response);
    printResult(response);
  }

  @ShellMethod(key = {"list-runner-catalog", "lrc"}, value = Descriptions.LIST_RUNNER_CATALOG)
  public void listRunnerCatalog(
      @ShellOption(value = {"-m", "--market-id"}, help = MARKET_ID) final String marketId) {

    var marketCatalogue = marketCache.getMarketCatalogue(marketId);
    printResult(marketCatalogue.getRunners());
  }

  @ShellMethod(key = {"list-runners", "lr"}, value = Descriptions.LIST_RUNNER_CATALOG)
  public void listRunners(
      @ShellOption(value = {"-m", "--market-id"}, help = MARKET_ID) final String marketId) {

    var marketBook = marketCache.getMarketBook(marketId);
    printResult(marketBook.getRunners());
  }

  @ShellMethod(key = {"list-market-book", "lmb"}, value = Descriptions.LIST_MARKET_BOOK)
  public void listMarketBook(
      @ShellOption(value = {"-m", "--market-ids"}, help = MARKET_ID) final List<String> marketIds) {

    var request = requestBuilder.getMarketBookRequest(marketIds);
    var response = bettingClient.listMarketBook(request);
    marketCache.cacheMarketBook(response);
    printResult(response);
  }

  private <T> void printResult(List<T> result) {
    if (result.isEmpty()) {
      shell.warn(Messages.EMPTY_RESULT);
      return;
    }
    shell.print(result);
  }
}

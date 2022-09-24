package io.github.aj8gh.bot.console.util;

import static java.util.stream.Collectors.toMap;

import io.github.aj8gh.bot.domain.betting.types.MarketBook;
import io.github.aj8gh.bot.domain.betting.types.MarketCatalogue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MarketCache {

  private final Map<String, MarketCatalogue> catalogueCache = new HashMap<>();
  private final Map<String, MarketBook> bookCache = new HashMap<>();

  public void cacheMarketCatalogue(List<MarketCatalogue> marketCatalogue) {
    var newCache = marketCatalogue.stream()
        .collect(toMap(MarketCatalogue::getMarketId, Function.identity()));
    catalogueCache.putAll(newCache);
  }

  public void cacheMarketBook(List<MarketBook> marketBook) {
    var newCache = marketBook.stream()
        .collect(toMap(MarketBook::getMarketId, Function.identity()));
    bookCache.putAll(newCache);
  }

  public MarketCatalogue getMarketCatalogue(String marketId) {
    return catalogueCache.get(marketId);
  }

  public MarketBook getMarketBook(String marketId) {
    return bookCache.get(marketId);
  }
}

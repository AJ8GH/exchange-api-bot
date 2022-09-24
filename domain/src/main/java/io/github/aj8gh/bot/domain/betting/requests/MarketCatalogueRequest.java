package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.enums.MarketProjection;
import io.github.aj8gh.bot.domain.betting.enums.MarketSort;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import java.util.Set;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class MarketCatalogueRequest {

  @NonNull MarketFilter filter;
  Set<MarketProjection> marketProjection;
  MarketSort sort;
  Integer maxResults;
  String locale;
}

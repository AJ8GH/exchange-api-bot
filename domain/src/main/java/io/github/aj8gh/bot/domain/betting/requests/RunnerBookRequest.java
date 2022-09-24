package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.enums.MatchProjection;
import io.github.aj8gh.bot.domain.betting.enums.OrderProjection;
import io.github.aj8gh.bot.domain.betting.types.PriceProjection;
import java.util.Date;
import java.util.Set;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class RunnerBookRequest {

  @NonNull String marketId;
  @NonNull Long selectionId;
  Double handicap;
  PriceProjection priceProjection;
  OrderProjection orderProjection;
  MatchProjection matchProjection;
  Boolean includeOverallPosition;
  Boolean partitionMatchedByStrategyRef;
  Set<String> customerStrategyRefs;
  String currencyCode;
  String locale;
  Date matchedSince;
  Set<String> betIds;
}

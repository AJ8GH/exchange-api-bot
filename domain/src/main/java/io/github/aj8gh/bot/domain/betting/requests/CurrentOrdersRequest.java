package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.enums.OrderBy;
import io.github.aj8gh.bot.domain.betting.enums.OrderProjection;
import io.github.aj8gh.bot.domain.betting.enums.SortDir;
import io.github.aj8gh.bot.domain.betting.types.TimeRange;
import java.util.Set;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CurrentOrdersRequest {

  @NonNull Set<String> betIds;
  Set<String> marketIds;
  OrderProjection orderProjection;
  TimeRange placedDateRange;
  OrderBy orderBy;
  SortDir sortDir;
  Integer fromRecord;
  Integer recordCount;
}

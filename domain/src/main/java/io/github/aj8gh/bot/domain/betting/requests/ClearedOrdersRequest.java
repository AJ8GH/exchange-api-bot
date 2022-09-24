package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.enums.BetStatus;
import io.github.aj8gh.bot.domain.betting.enums.GroupBy;
import io.github.aj8gh.bot.domain.betting.enums.Side;
import io.github.aj8gh.bot.domain.betting.types.RunnerId;
import io.github.aj8gh.bot.domain.betting.types.TimeRange;
import java.util.Set;
import lombok.NonNull;
import lombok.Value;

@Value
public class ClearedOrdersRequest {

  @NonNull BetStatus betStatus;
  Set<String> eventTypeIds;
  Set<String> eventIds;
  Set<String> marketIds;
  Set<RunnerId> runnerIds;
  Set<String> betIds;
  Side side;
  TimeRange settledDateRange;
  GroupBy groupBy;
  Boolean includeItemDescription;
  String locale;
  Integer fromRecord;
  Integer recordCount;
}

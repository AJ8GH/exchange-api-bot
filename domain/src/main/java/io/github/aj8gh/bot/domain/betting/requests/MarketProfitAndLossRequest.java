package io.github.aj8gh.bot.domain.betting.requests;

import java.util.Set;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class MarketProfitAndLossRequest {

  @NonNull Set<String> marketIds;
  Boolean includeSettledBets;
  Boolean includeBspBets;
  Boolean netOfCommission;
}

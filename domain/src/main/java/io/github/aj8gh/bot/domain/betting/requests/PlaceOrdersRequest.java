package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.types.MarketVersion;
import io.github.aj8gh.bot.domain.betting.types.PlaceInstruction;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class PlaceOrdersRequest {

  @NonNull String marketId;
  @NonNull List<PlaceInstruction> instructions;
  String customerRef;
  MarketVersion marketVersion;
  String customerStrategyRef;
  Boolean async;
}

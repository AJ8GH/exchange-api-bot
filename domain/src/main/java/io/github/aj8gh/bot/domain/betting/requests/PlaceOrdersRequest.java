package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.types.MarketVersion;
import io.github.aj8gh.bot.domain.betting.types.PlaceInstruction;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

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

package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.types.MarketVersion;
import io.github.aj8gh.bot.domain.betting.types.ReplaceInstruction;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class ReplaceOrdersRequest {
    @NonNull String marketId;
    @NonNull List<ReplaceInstruction> instructions;
    String customerRef;
    MarketVersion marketVersion;
    Boolean async;
}

package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.types.CancelInstruction;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CancelOrdersRequest {
    @NonNull String marketId;
    @NonNull List<CancelInstruction> instructions;
    String customerRef;
}

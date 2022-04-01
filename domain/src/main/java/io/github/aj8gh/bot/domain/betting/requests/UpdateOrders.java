package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.types.UpdateInstruction;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UpdateOrders {
    @NonNull String marketId;
    @NonNull List<UpdateInstruction> instructions;
    String customerRef;
}

package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.types.CancelInstruction;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class CancelOrdersRequest {

  @NonNull String marketId;
  @NonNull List<CancelInstruction> instructions;
  String customerRef;
}

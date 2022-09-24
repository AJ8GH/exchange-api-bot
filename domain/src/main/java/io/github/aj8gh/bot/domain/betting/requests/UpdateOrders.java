package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.types.UpdateInstruction;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class UpdateOrders {

  @NonNull String marketId;
  @NonNull List<UpdateInstruction> instructions;
  String customerRef;
}

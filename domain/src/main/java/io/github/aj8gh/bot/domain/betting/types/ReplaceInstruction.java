package io.github.aj8gh.bot.domain.betting.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplaceInstruction {

  @NonNull
  private String betId;
  @NonNull
  private Double newPrice;
}

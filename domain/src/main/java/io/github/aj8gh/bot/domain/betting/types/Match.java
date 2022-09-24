package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.Side;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {

  @NonNull
  private Side side;
  @NonNull
  private Double price;
  @NonNull
  private Double size;
  private String betId;
  private String matchId;
  private Date matchDate;
}

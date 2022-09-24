package io.github.aj8gh.bot.domain.betting.types;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartingPrices {

  private Double nearPrice;
  private Double farPrice;
  private List<PriceSize> backStakeTaken;
  private List<PriceSize> layLiabilityTaken;
  private Double actualSP;
}

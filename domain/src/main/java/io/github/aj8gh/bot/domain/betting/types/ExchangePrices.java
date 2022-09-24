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
public class ExchangePrices {

  private List<PriceSize> availableToBack;
  private List<PriceSize> availableToLay;
  private List<PriceSize> tradedVolume;
}

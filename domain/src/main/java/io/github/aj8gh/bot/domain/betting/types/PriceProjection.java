package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.PriceData;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceProjection {

  private Set<PriceData> priceData;
  private ExBestOffersOverrides exBestOffersOverrides;
  private Boolean virtualise;
  private Boolean rolloverStakes;
}

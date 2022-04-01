package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.RollupModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExBestOffersOverrides {
    private Integer bestPricesDepth;
    private RollupModel rollupModel;
    private Integer rollupLimit;
    private Double rollupLiabilityThreshold;
    private Integer rollupLiabilityFactor;
}

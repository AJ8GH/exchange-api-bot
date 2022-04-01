package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.MarketBettingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketDescription {
    @NonNull private Boolean persistenceEnabled;
    @NonNull private Boolean bspMarket;
    @NonNull private Date marketTime;
    @NonNull private Date suspendTime;
    @NonNull private MarketBettingType bettingType;
    @NonNull private Boolean turnInPlayEnabled;
    @NonNull private String marketType;
    @NonNull private String regulator;
    @NonNull private Double marketBaseRate;
    @NonNull private Boolean discountAllowed;
    private Date settleTime;
    private String wallet;
    private String rules;
    private Boolean rulesHasDate;
    private Double eachWayDivisor;
    private String clarifications;
    private MarketLineRangeInfo lineRangeInfo;
    private String raceType;
    private PriceLadderDescription priceLadderDescription;
}

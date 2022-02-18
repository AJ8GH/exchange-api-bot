package jonasa.exchangeapibot.betting.types;

import jonasa.exchangeapibot.betting.enums.MarketBettingType;
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
    @NonNull
    private Boolean persistenceEnabled;
    @NonNull
    private Boolean bspMarket;
    @NonNull
    private Date marketTime;
    @NonNull
    private Date suspendTime;

    private Date settleTime;
    @NonNull
    private MarketBettingType bettingType;
    @NonNull
    private Boolean turnInPlayEnabled;
    @NonNull
    private String marketType;
    @NonNull
    private String regulator;
    @NonNull
    private Double marketBaseRate;
    @NonNull
    private Boolean discountAllowed;

    private String wallet;

    private String rules;

    private Boolean rulesHasDate;

    private String clarifications;

    private MarketLineRangeInfo lineRangeInfo;

    private String raceType;

    private PriceLadderDescription priceLadderDescription;
}

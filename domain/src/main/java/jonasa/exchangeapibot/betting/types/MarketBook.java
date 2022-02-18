package jonasa.exchangeapibot.betting.types;

import jonasa.exchangeapibot.betting.enums.MarketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketBook {
    @NonNull
    private String marketId;
    @NonNull
    private Boolean isMarketDataDelayed;

    private MarketStatus status;

    private Integer betDelay;

    private Boolean bspReconciled;

    private Boolean complete;

    private Boolean inplay;

    private Integer numberOfWinners;

    private Integer numberOfRunners;

    private Integer numberOfActiveRunners;

    private Date lastMatchTime;

    private Double totalMatched;

    private Double totalAvailable;

    private Boolean crossMatching;

    private Boolean runnersVoidable;

    private Long version;

    private List<Runner> runners;

    private KeyLineDescription keyLineDescription;
}

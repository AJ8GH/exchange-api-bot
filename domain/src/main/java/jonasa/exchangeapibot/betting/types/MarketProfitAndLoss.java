package jonasa.exchangeapibot.betting.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketProfitAndLoss {

    private String marketId;

    private Double commissionApplied;

    private List<RunnerProfitAndLoss> profitAndLosses;
}

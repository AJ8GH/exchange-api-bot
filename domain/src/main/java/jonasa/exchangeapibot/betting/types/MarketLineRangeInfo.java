package jonasa.exchangeapibot.betting.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketLineRangeInfo {
    @NonNull
    private Double maxUnitValue;
    @NonNull
    private Double minUnitValue;
    @NonNull
    private Double interval;
    @NonNull
    private String marketUnit;
}

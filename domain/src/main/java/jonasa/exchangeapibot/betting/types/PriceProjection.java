package jonasa.exchangeapibot.betting.types;

import jonasa.exchangeapibot.betting.enums.PriceData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

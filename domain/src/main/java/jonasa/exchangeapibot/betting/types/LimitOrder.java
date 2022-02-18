package jonasa.exchangeapibot.betting.types;

import jonasa.exchangeapibot.betting.enums.BetTargetType;
import jonasa.exchangeapibot.betting.enums.PersistenceType;
import jonasa.exchangeapibot.betting.enums.TimeInForce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LimitOrder {
    @NonNull
    private Double size;
    @NonNull
    private Double price;
    @NonNull
    private PersistenceType persistenceType;

    private TimeInForce timeInForce;

    private Double minFillSize;

    private BetTargetType betTargetType;

    private Double betTargetSize;
}

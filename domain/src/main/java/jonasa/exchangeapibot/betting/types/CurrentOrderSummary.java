package jonasa.exchangeapibot.betting.types;

import jonasa.exchangeapibot.betting.enums.OrderStatus;
import jonasa.exchangeapibot.betting.enums.OrderType;
import jonasa.exchangeapibot.betting.enums.PersistenceType;
import jonasa.exchangeapibot.betting.enums.Side;
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
public class CurrentOrderSummary {
    @NonNull
    private String betId;
    @NonNull
    private String marketId;
    @NonNull
    private Long selectionId;
    @NonNull
    private Double handicap;
    @NonNull
    private PriceSize priceSize;
    @NonNull
    private Double bspLiability;
    @NonNull
    private Side side;
    @NonNull
    private OrderStatus status;
    @NonNull
    private PersistenceType persistenceType;
    @NonNull
    private OrderType orderType;
    @NonNull
    private Date placedDate;
    @NonNull
    private Date matchedDate;

    private Double averagePriceMatched;

    private Double sizeMatched;

    private Double sizeRemaining;

    private Double sizeLapsed;

    private Double sizeCancelled;

    private Double sizeVoided;

    private String regulatorAuthCode;

    private String regulatorCode;

    private String customerOrderRef;

    private String customerStrategyRef;

    private CurrentItemDescription currentItemDescription;
}

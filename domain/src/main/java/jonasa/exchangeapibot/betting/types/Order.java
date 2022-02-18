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
public class Order {
    @NonNull
    private String betId;
    @NonNull
    private OrderType orderType;
    @NonNull
    private OrderStatus status;
    @NonNull
    private PersistenceType persistenceType;
    @NonNull
    private Side side;
    @NonNull
    private Double price;
    @NonNull
    private Double size;
    @NonNull
    private Double bspLiability;
    @NonNull
    private Date placedDate;

    private Double avgPriceMatched;

    private Double sizeMatched;

    private Double sizeRemaining;

    private Double sizeLapsed;

    private Double sizeCancelled;

    private Double sizeVoided;

    private String customerOrderRef;

    private String customerStrategyRef;
}

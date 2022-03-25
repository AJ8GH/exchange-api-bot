package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.OrderType;
import io.github.aj8gh.bot.domain.betting.enums.Side;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceInstruction {
    @NonNull
    private OrderType orderType;
    @NonNull
    private Long selectionId;

    private Double handicap;
    @NonNull
    private Side side;

    private LimitOrder limitOrder;

    private LimitOnCloseOrder limitOnCloseOrder;

    private MarketOnCloseOrder marketOnCloseOrder;

    private String customerOrderRef;
}

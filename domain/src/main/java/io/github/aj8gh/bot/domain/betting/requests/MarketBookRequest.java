package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.enums.MatchProjection;
import io.github.aj8gh.bot.domain.betting.enums.OrderProjection;
import io.github.aj8gh.bot.domain.betting.types.PriceProjection;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Value
@Builder
public class MarketBookRequest {
    @NonNull List<String> marketIds;
    PriceProjection priceProjection;
    OrderProjection orderProjection;
    MatchProjection matchProjection;
    String currencyCode;
    String locale;
    Date matchedSince;
    Set<String> betId;
}

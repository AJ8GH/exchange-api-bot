package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.MarketBettingType;
import io.github.aj8gh.bot.domain.betting.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketFilter {

    private String textQuery;

    private Set<String> eventTypeIds;

    private Set<String> eventIds;

    private Set<String> competitionIds;

    private Set<String> marketIds;

    private Set<String> venues;

    private Boolean bspOnly;

    private Boolean turnInPlayEnabled;

    private Boolean inPlayOnly;

    private Set<MarketBettingType> marketBettingTypes;

    private Set<String> marketCountries;

    private Set<String> marketTypeCodes;

    private Set<TimeRange> marketStartTime;

    private Set<OrderStatus> withOrders;

    private Set<String> raceTypes;
}

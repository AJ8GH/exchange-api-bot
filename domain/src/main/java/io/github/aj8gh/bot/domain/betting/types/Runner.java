package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.RunnerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Runner {
    @NonNull private Long selectionId;
    @NonNull private Double handicap;
    @NonNull private RunnerStatus status;
    private Double adjustmentFactor;
    private Double lastPriceTraded;
    private Double totalMatched;
    private Date removalDate;
    private StartingPrices sp = new StartingPrices();
    private ExchangePrices ex = new ExchangePrices();
    private List<Order> orders;
    private List<Match> matches;
    private Map<String,List<Match>> matchesByStrategy;
}

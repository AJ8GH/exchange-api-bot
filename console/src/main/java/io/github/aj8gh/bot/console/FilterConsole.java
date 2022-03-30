package io.github.aj8gh.bot.console;

import io.github.aj8gh.bot.console.util.RequestManager;
import io.github.aj8gh.bot.console.util.constants.Descriptions;
import io.github.aj8gh.bot.domain.betting.enums.MarketBettingType;
import io.github.aj8gh.bot.domain.betting.enums.OrderStatus;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import io.github.aj8gh.bot.domain.betting.types.TimeRange;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Set;

import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
public class FilterConsole {
    private final RequestManager requestManager;

    public FilterConsole(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @ShellMethod(Descriptions.BUILD_MARKET_FILTER)
    public void buildMarketFilter(@ShellOption(defaultValue = NULL, value={"-t", "--text-query"}) final String textQuery,
                                  @ShellOption(defaultValue = NULL, value={"-et", "--event-type-ids"}) final Set<String> eventTypeIds,
                                  @ShellOption(defaultValue = NULL, value={"-e", "--event-ids"}) final Set<String> eventIds,
                                  @ShellOption(defaultValue = NULL, value={"-c", "--competition-ids"}) final Set<String> competitionIds,
                                  @ShellOption(defaultValue = NULL, value={"-m", "--market-ids"}) final Set<String> marketIds,
                                  @ShellOption(defaultValue = NULL, value={"-v", "--venues"}) final Set<String> venues,
                                  @ShellOption(defaultValue = NULL, value={"-bsp", "--bsp-only"}) final Boolean bspOnly,
                                  @ShellOption(defaultValue = NULL, value={"-tip", "--turn-in-play"}) final Boolean turnInPlayEnabled,
                                  @ShellOption(defaultValue = NULL, value={"-ipo", "--in-play-only"}) final Boolean inPlayOnly,
                                  @ShellOption(defaultValue = NULL, value={"-mb", "--market-betting-types"}) final Set<MarketBettingType> marketBettingTypes,
                                  @ShellOption(defaultValue = NULL, value={"-mc", "--market-countries"}) final Set<String> marketCountries,
                                  @ShellOption(defaultValue = NULL, value={"-mtc", "--market-type-codes"}) final Set<String> marketTypeCodes,
                                  @ShellOption(defaultValue = NULL, value={"-mst", "--market-start-time"}) final Set<TimeRange> marketStartTime,
                                  @ShellOption(defaultValue = NULL, value={"-wo", "--with-orders"}) final Set<OrderStatus> withOrders,
                                  @ShellOption(defaultValue = NULL, value={"-rt", "--race-types"}) final Set<String> raceTypes) {

        var filter = MarketFilter.builder()
                .textQuery(textQuery)
                .eventTypeIds(eventTypeIds)
                .eventIds(eventIds)
                .competitionIds(competitionIds)
                .marketIds(marketIds)
                .venues(venues)
                .bspOnly(bspOnly)
                .turnInPlayEnabled(turnInPlayEnabled)
                .inPlayOnly(inPlayOnly)
                .marketBettingTypes(marketBettingTypes)
                .marketCountries(marketCountries)
                .marketTypeCodes(marketTypeCodes)
                .marketStartTime(marketStartTime)
                .withOrders(withOrders)
                .raceTypes(raceTypes)
                .build();

        requestManager.setMarketFilter(filter);
    }

    @ShellMethod(Descriptions.CLEAR_MARKET_FILTER)
    public void clearMarketFilter() {
        requestManager.clearMarketFilter();
    }
}

package io.github.aj8gh.bot.console;

import io.github.aj8gh.bot.betting.client.RequestBuilder;
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
public class RequestConsole {
    private final RequestBuilder requestBuilder;

    public RequestConsole(RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    @ShellMethod(key = {"build-market-filter", "bmf"}, value = Descriptions.BUILD_MARKET_FILTER)
    public void buildMarketFilter(
            @ShellOption(defaultValue = NULL, value={"-t", "--text-query"}, help = Descriptions.TEXT_QUERY) final String textQuery,
            @ShellOption(defaultValue = NULL, value={"-et", "--event-type-ids"}, help = Descriptions.EVENT_TYPE_IDS) final Set<String> eventTypeIds,
            @ShellOption(defaultValue = NULL, value={"-e", "--event-ids"}, help = Descriptions.EVENT_IDS) final Set<String> eventIds,
            @ShellOption(defaultValue = NULL, value={"-c", "--competition-ids"}, help = Descriptions.COMPETITION_IDS) final Set<String> competitionIds,
            @ShellOption(defaultValue = NULL, value={"-m", "--market-ids"}, help = Descriptions.MARKET_IDS) final Set<String> marketIds,
            @ShellOption(defaultValue = NULL, value={"-v", "--venues"}, help = Descriptions.VENUES) final Set<String> venues,
            @ShellOption(defaultValue = NULL, value={"-bsp", "--bsp-only"}, help = Descriptions.BSP_ONLY) final Boolean bspOnly,
            @ShellOption(defaultValue = NULL, value={"-tip", "--turn-in-play"}, help = Descriptions.TURN_IN_PLAY_ENABLED) final Boolean turnInPlayEnabled,
            @ShellOption(defaultValue = NULL, value={"-ipo", "--in-play-only"}, help = Descriptions.IN_PLAY_ONLY) final Boolean inPlayOnly,
            @ShellOption(defaultValue = NULL, value={"-mb", "--market-betting-types"}, help = Descriptions.MARKET_BETTING_TYPES) final Set<MarketBettingType> marketBettingTypes,
            @ShellOption(defaultValue = NULL, value={"-mc", "--market-countries"}, help = Descriptions.MARKET_COUNTRIES) final Set<String> marketCountries,
            @ShellOption(defaultValue = NULL, value={"-mtc", "--market-type-codes"}, help = Descriptions.MARKET_TYPE_CODES) final Set<String> marketTypeCodes,
            @ShellOption(defaultValue = NULL, value={"-mst", "--market-start-time"}, help = Descriptions.MARKET_START_TIME) final Set<TimeRange> marketStartTime,
            @ShellOption(defaultValue = NULL, value={"-wo", "--with-orders"}, help = Descriptions.WITH_ORDERS) final Set<OrderStatus> withOrders,
            @ShellOption(defaultValue = NULL, value={"-rt", "--race-types"}, help = Descriptions.RACE_TYPES) final Set<String> raceTypes) {

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

        requestBuilder.setMarketFilter(filter);
    }

    @ShellMethod(Descriptions.CLEAR_MARKET_FILTER)
    public void clearMarketFilter() {
        requestBuilder.clearMarketFilter();
    }
}

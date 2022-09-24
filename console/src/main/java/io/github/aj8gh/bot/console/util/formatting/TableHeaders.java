package io.github.aj8gh.bot.console.util.formatting;

import io.github.aj8gh.bot.domain.betting.types.CompetitionResult;
import io.github.aj8gh.bot.domain.betting.types.CountryCodeResult;
import io.github.aj8gh.bot.domain.betting.types.EventResult;
import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketBook;
import io.github.aj8gh.bot.domain.betting.types.MarketCatalogue;
import io.github.aj8gh.bot.domain.betting.types.MarketTypeResult;
import io.github.aj8gh.bot.domain.betting.types.Runner;
import io.github.aj8gh.bot.domain.betting.types.RunnerCatalog;
import io.github.aj8gh.bot.domain.betting.types.TimeRangeResult;
import io.github.aj8gh.bot.domain.betting.types.VenueResult;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TableHeaders {

  private static final Map<Class<?>, List<String>> HEADERS = Map.ofEntries(
      Map.entry(EventTypeResult.class, List.of(
          "eventType.id", "ID",
          "eventType.name", "Event Type",
          "marketCount", "Market Count")),
      Map.entry(EventResult.class, List.of(
          "event.id", "ID",
          "event.name", "Event",
          "event.countryCode", "Country Code",
          "event.timezone", "Timezone",
          "event.venue", "Venue",
          "event.openDate", "Open Date",
          "marketCount", "Market Count")),
      Map.entry(CompetitionResult.class, List.of(
          "competition.id", "ID",
          "competition.name", "Competition",
          "marketCount", "Market Count",
          "competitionRegion", "Region")),
      Map.entry(MarketTypeResult.class, List.of(
          "marketType", "Market Type",
          "marketCount", "Market Count")),
      Map.entry(CountryCodeResult.class, List.of(
          "countryCode", "Country Code",
          "marketCount", "Market Count")),
      Map.entry(VenueResult.class, List.of(
          "venue", "Venue",
          "marketCount", "Market Count")),
      Map.entry(TimeRangeResult.class, List.of(
          "timeRange.from", "From",
          "timeRange.to", "To",
          "marketCount", "Market Count")),
      Map.entry(MarketCatalogue.class, List.of(
          "marketId", "Market ID",
          "marketName", "Market Name",
          "marketStartTime", "Market Start Time",
          "totalMatched", "Total Matched",
          "eventType.name", "Event Type",
          "competition.id", "Competition ID",
          "competition.name", "Competition",
          "event.id", "Event ID",
          "event.name", "Event")),
      Map.entry(RunnerCatalog.class, List.of(
          "selectionId", "Selection ID",
          "runnerName", "Runner Name",
          "handicap", "Handicap",
          "sortPriority", "Sort Priority",
          "metadata", "Metadata")),
      Map.entry(MarketBook.class, List.of(
          "marketId", "Market ID",
          "isMarketDataDelayed", "Is Delayed",
          "status", "Status",
          "betDelay", "Bet Delay",
          "bspReconciled", "BSP Reconciled",
          "complete", "Complete",
          "inplay", "In Play",
          "numberOfWinners", "Number Of Winners",
          "numberOfActiveRunners", "Active Runners",
          "lastMatchTime", "Last Match Time",
          "totalMatched", "Total Matched",
          "totalAvailable", "Total Available",
          "crossMatching", "Cross Matching",
          "runnersVoidable", "Runners Voidable",
          "version", "Version",
          "keyLineDescription", "Key Line Description")),
      Map.entry(Runner.class, List.of(
          "selectionId", "Selection ID",
          "handicap", "Handicap",
          "status", "Status",
          "adjustmentFactor", "Adjustment Factor",
          "lastPriceTraded", "Last Price Traded",
          "totalMatched", "Total Matched",
          "removalDate", "Removal Date",
          "sp.nearPrice", "Near Price",
          "sp.farPrice", "Far Price",
          "sp.backStakeTaken", "Back Stake Taken",
          "sp.layLiabilityTaken", "Lay Liability Taken",
          "sp.actualSP", "Actual SP",
          "ex.availableToBack", "ATB",
          "ex.availableToLay", "ATL",
          "ex.tradedVolume", "Traded Volume",
          "matchesByStrategy", "Matches By Strategy"
      ))
  );

  private final Map<Class<?>, Map<String, Object>> headerMaps;

  public TableHeaders() {
    this.headerMaps = buildHeadersMaps();
  }

  public Map<String, Object> get(Class<?> clazz) {
    return headerMaps.get(clazz);
  }

  private Map<Class<?>, Map<String, Object>> buildHeadersMaps() {
    Map<Class<?>, Map<String, Object>> map = new HashMap<>();
    for (Map.Entry<Class<?>, List<String>> entry : HEADERS.entrySet()) {
      Map<String, Object> header = new LinkedHashMap<>();
      var list = entry.getValue();
      for (int i = 0; i < list.size(); i += 2) {
        header.put(list.get(i), list.get(i + 1));
      }
      map.put(entry.getKey(), header);
    }
    return map;
  }
}

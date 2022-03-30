package io.github.aj8gh.bot.console.util.formatting;

import io.github.aj8gh.bot.domain.betting.types.CompetitionResult;
import io.github.aj8gh.bot.domain.betting.types.CountryCodeResult;
import io.github.aj8gh.bot.domain.betting.types.EventResult;
import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketTypeResult;
import io.github.aj8gh.bot.domain.betting.types.VenueResult;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TableHeaders {
    private static final Map<Class<?>, List<String>> HEADERS = Map.of(
            EventTypeResult.class, List.of(
                    "eventType.id", "ID",
                    "eventType.name", "Event Type",
                    "marketCount", "Market Count"),
            EventResult.class, List.of(
                    "event.id", "ID",
                    "event.name", "Event",
                    "event.countryCode", "Country Code",
                    "event.timezone", "Timezone",
                    "event.venue", "Venue",
                    "event.openDate", "Open Date",
                    "marketCount", "Market Count"),
            CompetitionResult.class, List.of(
                    "competition.id", "ID",
                    "competition.name", "Competition",
                    "marketCount", "Market Count",
                    "competitionRegion", "Region"),
            MarketTypeResult.class, List.of(
                    "marketType", "Market Type",
                    "marketCount", "Market Count"),
            CountryCodeResult.class, List.of(
                    "countryCode", "Country Code",
                    "marketCount", "Market Count"),
            VenueResult.class, List.of(
                    "venue", "Venue",
                    "marketCount", "Market Count")
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
             for (int i = 0; i < list.size(); i+= 2) {
                 header.put(list.get(i), list.get(i + 1));
             }
             map.put(entry.getKey(), header);
        }
        return map;
    }
}

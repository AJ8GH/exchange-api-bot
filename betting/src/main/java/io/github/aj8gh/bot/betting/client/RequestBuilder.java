package io.github.aj8gh.bot.betting.client;

import io.github.aj8gh.bot.domain.betting.enums.MarketProjection;
import io.github.aj8gh.bot.domain.betting.enums.MarketSort;
import io.github.aj8gh.bot.domain.betting.enums.TimeGranularity;
import io.github.aj8gh.bot.domain.betting.requests.MarketBookRequest;
import io.github.aj8gh.bot.domain.betting.requests.MarketCatalogueRequest;
import io.github.aj8gh.bot.domain.betting.requests.MarketFilterRequest;
import io.github.aj8gh.bot.domain.betting.requests.TimeRangeRequest;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static io.github.aj8gh.bot.domain.betting.enums.MarketSort.FIRST_TO_START;
import static io.github.aj8gh.bot.domain.betting.enums.TimeGranularity.DAYS;

public class RequestBuilder {
    private static final TimeGranularity DEFAULT_GRANULARITY = DAYS;
    private static final Set<MarketProjection> DEFAULT_MARKET_PROJECTION = EnumSet.allOf(MarketProjection.class);
    private static final MarketSort DEFAULT_MARKET_SORT = FIRST_TO_START;
    private static final Integer DEFAULT_MAX_RESULTS = 50;

    private MarketFilter marketFilter = MarketFilter.builder().build();
    private TimeGranularity timeGranularity = DEFAULT_GRANULARITY;
    private Set<MarketProjection> marketProjection = DEFAULT_MARKET_PROJECTION;
    private MarketSort marketSort = DEFAULT_MARKET_SORT;
    private Integer maxResults = DEFAULT_MAX_RESULTS;

    public MarketFilterRequest getMarketFilterRequest() {
        return MarketFilterRequest.builder()
                .filter(marketFilter)
                .build();
    }

    public TimeRangeRequest getTimeRangeRequest(TimeGranularity granularity) {
        return TimeRangeRequest.builder().filter(marketFilter)
                .granularity(granularity == null ? timeGranularity : granularity)
                .build();
    }

    public MarketCatalogueRequest getMarketCatalogueRequest(Set<MarketProjection> projection,
                                                            MarketSort sort,
                                                            Integer max) {
        return MarketCatalogueRequest.builder().filter(marketFilter)
                .marketProjection(projection == null ? this.marketProjection : projection)
                .sort(sort == null ? marketSort : sort)
                .maxResults(max == null ? maxResults : max)
                .build();
    }

    public MarketBookRequest getMarketBookRequest(List<String> marketIds) {
        return MarketBookRequest.builder()
                .marketIds(marketIds)
                .build();
    }

    public void setMarketFilter(MarketFilter marketFilter) {
        this.marketFilter = marketFilter;
    }

    public void clearMarketFilter() {
        this.marketFilter = MarketFilter.builder().build();
    }

    public void setGranularity(TimeGranularity timeGranularity) {
        this.timeGranularity = timeGranularity;
    }

    public void setMarketProjection(Set<MarketProjection> marketProjection) {
        this.marketProjection = marketProjection;
    }

    public void setMarketSort(MarketSort marketSort) {
        this.marketSort = marketSort;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }
}

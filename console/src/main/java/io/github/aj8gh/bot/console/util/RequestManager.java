package io.github.aj8gh.bot.console.util;

import io.github.aj8gh.bot.domain.betting.types.MarketFilter;

public class RequestManager {
    private MarketFilter marketFilter = new MarketFilter();

    public MarketFilter getMarketFilter() {
        return marketFilter;
    }

    public void setMarketFilter(MarketFilter marketFilter) {
        this.marketFilter = marketFilter;
    }

    public void clearMarketFilter() {
        this.marketFilter = new MarketFilter();
    }
}

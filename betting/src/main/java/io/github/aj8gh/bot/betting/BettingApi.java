package io.github.aj8gh.bot.betting;

import io.github.aj8gh.bot.betting.cache.BettingCache;
import io.github.aj8gh.bot.betting.client.BettingClient;
import io.github.aj8gh.bot.betting.client.RequestBuilder;
import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;

import java.util.List;

public class BettingApi {
    private final BettingClient bettingClient;
    private final BettingCache bettingCache;
    private final RequestBuilder requestBuilder;

    public BettingApi(BettingClient bettingClient,
                      BettingCache bettingCache,
                      RequestBuilder requestBuilder) {
        this.bettingClient = bettingClient;
        this.bettingCache = bettingCache;
        this.requestBuilder = requestBuilder;
    }

    public List<EventTypeResult> listEventTypes() {
        return bettingClient.listEventTypes(requestBuilder.getMarketFilterRequest());
    }
}

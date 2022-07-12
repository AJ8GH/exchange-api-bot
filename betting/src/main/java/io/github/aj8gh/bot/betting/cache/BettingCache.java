package io.github.aj8gh.bot.betting.cache;

import io.github.aj8gh.bot.betting.client.BettingClient;
import io.github.aj8gh.bot.domain.betting.requests.MarketCatalogueRequest;
import io.github.aj8gh.bot.domain.betting.types.MarketCatalogue;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import io.github.aj8gh.bot.domain.betting.types.RunnerCatalog;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toMap;

public class BettingCache {
    private final BettingClient client;
    private final Map<String, MarketCatalogue> cache;

    public BettingCache(BettingClient client,
                        Map<String, MarketCatalogue> cache) {
        this.client = client;
        this.cache = cache;
    }

    public List<RunnerCatalog> getRunnerCatalogue(String marketId) {
        var marketCatalogue = cache.get(marketId);
        if (marketCatalogue != null) return marketCatalogue.getRunners();

        var request = MarketCatalogueRequest.builder()
                .filter(MarketFilter.builder()
                        .marketIds(Set.of(marketId))
                        .build())
                .build();

        var response = client.listMarketCatalogue(request);
        if (!response.isEmpty()) return response.get(0).getRunners();
        return emptyList();
    }

    private void cacheMarketCatalogue(List<MarketCatalogue> marketCatalogue) {
        cache.putAll(marketCatalogue.stream()
                .collect(toMap(MarketCatalogue::getMarketId, Function.identity())));
    }
}

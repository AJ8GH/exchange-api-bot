package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class MarketFilterRequest {
    @NonNull MarketFilter filter;
    String locale;
}

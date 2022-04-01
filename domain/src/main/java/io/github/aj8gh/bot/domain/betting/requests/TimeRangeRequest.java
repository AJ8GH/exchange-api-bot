package io.github.aj8gh.bot.domain.betting.requests;

import io.github.aj8gh.bot.domain.betting.enums.TimeGranularity;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class TimeRangeRequest {
    @NonNull MarketFilter filter;
    @NonNull TimeGranularity granularity;
}

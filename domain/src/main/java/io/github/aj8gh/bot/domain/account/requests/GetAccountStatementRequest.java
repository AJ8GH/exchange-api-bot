package io.github.aj8gh.bot.domain.account.requests;

import io.github.aj8gh.bot.domain.account.enums.IncludeItem;
import io.github.aj8gh.bot.domain.account.enums.Wallet;
import io.github.aj8gh.bot.domain.account.types.TimeRange;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetAccountStatementRequest {
    String locale;
    Integer fromRecord;
    Integer recordCount;
    TimeRange itemDateRange;
    IncludeItem includeItem;
    Wallet wallet;
}

package io.github.aj8gh.bot.domain.account.requests;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ListCurrencyRatesRequest {
    String fromCurrency;
}

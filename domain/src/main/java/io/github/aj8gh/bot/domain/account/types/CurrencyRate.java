package io.github.aj8gh.bot.domain.account.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRate {
    private String currencyCode;
    private Double rate;
}

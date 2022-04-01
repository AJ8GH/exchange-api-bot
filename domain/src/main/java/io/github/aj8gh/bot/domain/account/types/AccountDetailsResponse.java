package io.github.aj8gh.bot.domain.account.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailsResponse {
    private String currencyCode;
    private String firstName;
    private String lastName;
    private String localeCode;
    private String region;
    private String timezone;
    private Double discountRate;
    private Integer pointsBalance;
    private String countryCode;
}

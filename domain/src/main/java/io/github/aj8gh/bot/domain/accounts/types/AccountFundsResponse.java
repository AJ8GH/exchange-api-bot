package io.github.aj8gh.bot.domain.accounts.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountFundsResponse {

    private Double availableToBetBalance;

    private Double exposure;

    private Double retainedCommission;

    private Double exposureLimit;

    private Double discountRate;

    private Integer pointsBalance;
}

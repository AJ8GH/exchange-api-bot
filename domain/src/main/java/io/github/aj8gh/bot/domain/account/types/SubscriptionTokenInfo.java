package io.github.aj8gh.bot.domain.account.types;

import io.github.aj8gh.bot.domain.account.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionTokenInfo {
    @NonNull
    private String subscriptionToken;

    private Date activatedDateTime;

    private Date expiryDateTime;

    private Date expiredDateTime;

    private Date cancellationDateTime;

    private SubscriptionStatus subscriptionStatus;
}

package io.github.aj8gh.bot.domain.account.requests;

import io.github.aj8gh.bot.domain.account.enums.SubscriptionStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ListApplicationSubscriptionTokensRequest {
    SubscriptionStatus subscriptionStatus;
}

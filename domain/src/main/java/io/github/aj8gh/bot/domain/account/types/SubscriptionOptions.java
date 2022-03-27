package io.github.aj8gh.bot.domain.account.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionOptions {

    private Integer subscriptionLength;

    private String subscriptionToken;

    private String clientReference;
}

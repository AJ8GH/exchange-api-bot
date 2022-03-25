package io.github.aj8gh.bot.domain.accounts.types;

import io.github.aj8gh.bot.domain.accounts.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationSubscription {

    private String subscriptionToken;

    private Date expiryDateTime;

    private Date expiredDateTime;

    private Date createdDateTime;

    private Date activationDateTime;

    private Date cancellationDateTime;

    private SubscriptionStatus subscriptionStatus;

    private String clientReference;

    private String vendorClientId;
}

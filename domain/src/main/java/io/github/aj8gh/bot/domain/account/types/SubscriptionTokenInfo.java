package io.github.aj8gh.bot.domain.account.types;

import io.github.aj8gh.bot.domain.account.enums.SubscriptionStatus;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

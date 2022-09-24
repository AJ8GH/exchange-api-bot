package io.github.aj8gh.bot.domain.account.types;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountSubscription {

  @NonNull
  private List<SubscriptionTokenInfo> subscriptionTokens;
  private String applicationName;
  private String applicationVersionId;
}

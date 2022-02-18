package jonasa.exchangeapibot.accounts.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

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

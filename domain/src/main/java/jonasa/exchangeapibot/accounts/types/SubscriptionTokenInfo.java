package jonasa.exchangeapibot.accounts.types;

import jonasa.exchangeapibot.accounts.enums.SubscriptionStatus;
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

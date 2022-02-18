package jonasa.exchangeapibot.accounts.types;

import jonasa.exchangeapibot.accounts.enums.AffiliateRelationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AffiliateRelation {
    @NonNull
    private String vendorClientId;
    @NonNull
    private AffiliateRelationStatus status;
}

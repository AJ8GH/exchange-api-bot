package io.github.aj8gh.bot.domain.accounts.types;

import io.github.aj8gh.bot.domain.accounts.enums.AffiliateRelationStatus;
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

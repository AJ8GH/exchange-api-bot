package io.github.aj8gh.bot.domain.account.requests;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class GetAffiliateRelationRequest {
    @NonNull List<String> vendorClientIds;
}

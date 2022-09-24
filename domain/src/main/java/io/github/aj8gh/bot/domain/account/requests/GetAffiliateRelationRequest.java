package io.github.aj8gh.bot.domain.account.requests;

import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class GetAffiliateRelationRequest {

  @NonNull List<String> vendorClientIds;
}

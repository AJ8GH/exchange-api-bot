package io.github.aj8gh.bot.domain.account.requests;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class IsAccountSubscribedToWebAppRequest {

  @NonNull
  String vendorId;
}

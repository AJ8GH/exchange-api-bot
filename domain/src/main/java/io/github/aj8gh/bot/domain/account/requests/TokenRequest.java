package io.github.aj8gh.bot.domain.account.requests;

import io.github.aj8gh.bot.domain.account.enums.GrantType;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class TokenRequest {

  @NonNull String client_id;
  @NonNull GrantType grant_type;
  @NonNull String client_secret;
  String refresh_token;
  String code;
}

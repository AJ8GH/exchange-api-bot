package io.github.aj8gh.bot.domain.account.types;

import io.github.aj8gh.bot.domain.account.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorAccessTokenInfo {
    @NonNull private String accessToken;
    @NonNull private TokenType tokenType;
    @NonNull private Long expiresIn;
    @NonNull private String refreshToken;
    @NonNull private ApplicationSubscription applicationSubscription;
}

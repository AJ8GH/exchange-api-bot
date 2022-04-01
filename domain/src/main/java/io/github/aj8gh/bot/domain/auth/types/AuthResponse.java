package io.github.aj8gh.bot.domain.auth.types;

import io.github.aj8gh.bot.domain.auth.enums.AuthErrorCode;
import io.github.aj8gh.bot.domain.auth.enums.AuthStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String product;
    private AuthStatus status;
    private AuthErrorCode error;
}

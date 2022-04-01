package io.github.aj8gh.bot.domain.heartbeat.requests;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class HeartbeatRequest {
    @NonNull Integer preferredTimeoutSeconds;
}

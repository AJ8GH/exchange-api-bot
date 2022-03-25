package io.github.aj8gh.bot.domain.heartbeat.types;

import io.github.aj8gh.bot.domain.heartbeat.enums.ActionPerformed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeartbeatReport {
    @NonNull
    private ActionPerformed actionPerformed;
    @NonNull
    private Integer actualTimeoutSeconds;
}

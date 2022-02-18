package jonasa.exchangeapibot.heartbeat.types;

import jonasa.exchangeapibot.heartbeat.enums.ActionPerformed;
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

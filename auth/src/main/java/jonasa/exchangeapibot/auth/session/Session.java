package jonasa.exchangeapibot.auth.session;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Data
@RequiredArgsConstructor
public final class Session {
    private final Instant createTime = Instant.now();
    @NonNull
    private final String token;

    private final String product;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Instant updateTime;

    public void keepAlive() {
        this.updateTime = Instant.now();
    }

    public Instant lastUpdated() {
        return updateTime == null ? createTime : updateTime;
    }
}

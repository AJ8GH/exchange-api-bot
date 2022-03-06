package jonasa.exchangeapibot.auth.session;

import jonasa.exchangeapibot.auth.client.AuthClient;

import java.time.Clock;
import java.util.function.Supplier;

public class SessionProvider implements Supplier<String> {

    private final AuthClient authClient;
    private final Clock clock;
    private final long sessionTtl;
    private final long keepAliveFreq;

    private long sessionCreatedTime = Long.MIN_VALUE;
    private String currentSession;

    public SessionProvider(AuthClient authClient,
                           Clock clock,
                           long sessionTtl,
                           long keepAliveFreq) {
        this.clock = clock;
        this.authClient = authClient;
        this.sessionTtl = sessionTtl;
        this.keepAliveFreq = keepAliveFreq;
    }

    @Override
    public String get() {
        return isActive() ? currentSession : authClient.login()
                .map(r -> {
                    currentSession = r.getToken();
                    sessionCreatedTime = clock.millis();
                    return currentSession;
                }).orElse(null);
    }

    boolean isActive() {
        return clock.millis() - sessionTtl < sessionCreatedTime;
    }

    public long getSessionTtl() {
        return sessionTtl;
    }

    public long getKeepAliveFreq() {
        return keepAliveFreq;
    }
}

package io.github.aj8gh.bot.auth.session;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import io.github.aj8gh.bot.auth.client.AuthClient;
import java.time.Clock;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.function.Supplier;

public class SessionSupplier implements Supplier<Session> {

  private final AuthClient authClient;
  private final ScheduledExecutorService scheduler;
  private final Clock clock;

  private ScheduledFuture<?> future;
  private Session session;
  private long keepAliveFreq;
  private long sessionTtl;

  public SessionSupplier(AuthClient authClient,
      ScheduledExecutorService scheduler,
      Clock clock) {
    this.clock = clock;
    this.authClient = authClient;
    this.scheduler = scheduler;
  }

  @Override
  public Session get() {
    return isActive() ? session : login();
  }

  public void setSessionTtl(long sessionTtl) {
    this.sessionTtl = sessionTtl;
  }

  public void setKeepAliveFreq(long keepAliveFreq) {
    this.keepAliveFreq = keepAliveFreq;
  }

  private Session login() {
    return authClient.login().map(response -> {
      session = new Session(response.getToken(), response.getProduct());
      if (future == null) {
        scheduleKeepAlive();
      }
      return session;
    }).orElse(null);
  }

  private void scheduleKeepAlive() {
    final Runnable job = this::keepAlive;
    future = scheduler.scheduleAtFixedRate(
        job, keepAliveFreq, keepAliveFreq, MILLISECONDS);
  }

  private void keepAlive() {
    authClient.keepAlive(get().getToken())
        .ifPresent(response -> session.keepAlive());
  }

  private boolean isActive() {
    return session != null && session.getUpdateTime()
        .isAfter(clock.instant().minusMillis(sessionTtl));
  }
}

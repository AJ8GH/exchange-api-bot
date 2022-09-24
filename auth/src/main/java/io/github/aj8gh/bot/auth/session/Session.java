package io.github.aj8gh.bot.auth.session;

import java.time.Instant;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

  public Instant getUpdateTime() {
    return updateTime == null ? createTime : updateTime;
  }
}

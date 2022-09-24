package io.github.aj8gh.bot.http.operations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HeartbeatOperations {
  HEARTBEAT("heartbeat");

  private final String value;
}

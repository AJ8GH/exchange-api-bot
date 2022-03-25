package io.github.aj8gh.bot.domain.heartbeat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operations {
    HEARTBEAT("heartbeat");

    private final String value;
}

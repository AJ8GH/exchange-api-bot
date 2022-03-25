package io.github.aj8gh.bot.domain.heartbeat.enums;

public enum ActionPerformed {
    NONE,
    CANCELLATION_REQUEST_SUBMITTED,
    ALL_BETS_CANCELLED,
    SOME_BETS_NOT_CANCELLED,
    CANCELLATION_REQUEST_ERROR,
    CANCELLATION_STATUS_UNKNOWN
}

package io.github.aj8gh.bot.domain.betting.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MarketType {
    A("Asian Handicap"),
    L("Line market"),
    O("Odds market"),
    R("Range market."),
    NOT_APPLICABLE("The market does not have an applicable marketType");

    private final String description;
}

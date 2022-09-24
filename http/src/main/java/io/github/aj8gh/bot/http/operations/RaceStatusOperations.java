package io.github.aj8gh.bot.http.operations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RaceStatusOperations {
  LIST_RACE_DETAILS("listRaceDetails");

  private final String value;
}

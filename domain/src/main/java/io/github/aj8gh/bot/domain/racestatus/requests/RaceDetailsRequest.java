package io.github.aj8gh.bot.domain.racestatus.requests;

import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RaceDetailsRequest {

  Set<String> meetingIds;
  Set<String> raceIds;
}

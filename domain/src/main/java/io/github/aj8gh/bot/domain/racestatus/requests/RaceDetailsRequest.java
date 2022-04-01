package io.github.aj8gh.bot.domain.racestatus.requests;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class RaceDetailsRequest {
    Set<String> meetingIds;
    Set<String> raceIds;
}

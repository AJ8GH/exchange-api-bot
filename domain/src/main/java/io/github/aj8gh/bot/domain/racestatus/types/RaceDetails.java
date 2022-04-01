package io.github.aj8gh.bot.domain.racestatus.types;

import io.github.aj8gh.bot.domain.racestatus.enums.RaceStatus;
import io.github.aj8gh.bot.domain.racestatus.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaceDetails {
    @NonNull private String meetingId;
    @NonNull private String raceId;
    @NonNull private RaceStatus raceStatus;
    @NonNull private Date lastUpdated;
    @NonNull private Long sequence;
    @NonNull private ResponseCode responseCode;
}

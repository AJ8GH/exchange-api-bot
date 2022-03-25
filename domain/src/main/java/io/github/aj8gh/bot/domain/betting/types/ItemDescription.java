package io.github.aj8gh.bot.domain.betting.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDescription {

    private String eventTypeDesc;

    private String eventDesc;

    private String marketDesc;

    private String marketType;

    private Date marketStartTime;

    private String runnerDesc;

    private Integer numberOfWinners;

    private Double eachWayDivisor;
}

package io.github.aj8gh.bot.domain.betting.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RunnerProfitAndLoss {
    private Long selectionId;
    private Double ifWin;
    private Double ifLose;
    private Double ifPlace;
}

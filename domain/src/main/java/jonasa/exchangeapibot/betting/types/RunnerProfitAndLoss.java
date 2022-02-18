package jonasa.exchangeapibot.betting.types;

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

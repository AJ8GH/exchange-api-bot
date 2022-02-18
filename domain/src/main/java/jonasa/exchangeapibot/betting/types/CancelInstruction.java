package jonasa.exchangeapibot.betting.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelInstruction {

    private String betId;

    private Double sizeReduction;
}

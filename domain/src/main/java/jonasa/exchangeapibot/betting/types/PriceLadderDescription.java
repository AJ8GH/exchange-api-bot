package jonasa.exchangeapibot.betting.types;

import jonasa.exchangeapibot.betting.enums.PriceLadderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceLadderDescription {
    @NonNull
    private PriceLadderType type;
}

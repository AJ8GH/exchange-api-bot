package jonasa.exchangeapibot.betting.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RunnerId {
    @NonNull
    private String marketId;
    @NonNull
    private Long selectionId;

    private Double handicap;
}

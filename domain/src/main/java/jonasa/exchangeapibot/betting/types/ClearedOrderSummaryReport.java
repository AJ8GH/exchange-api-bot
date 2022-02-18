package jonasa.exchangeapibot.betting.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClearedOrderSummaryReport {
    @NonNull
    private List<ClearedOrderSummary> clearedOrders;
    @NonNull
    private Boolean moreAvailable;
}

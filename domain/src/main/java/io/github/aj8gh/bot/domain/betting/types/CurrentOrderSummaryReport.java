package io.github.aj8gh.bot.domain.betting.types;

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
public class CurrentOrderSummaryReport {
    @NonNull private List<CurrentOrderSummary> currentOrders;
    @NonNull private Boolean moreAvailable;
}

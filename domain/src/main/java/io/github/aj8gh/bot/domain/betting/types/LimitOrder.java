package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.BetTargetType;
import io.github.aj8gh.bot.domain.betting.enums.PersistenceType;
import io.github.aj8gh.bot.domain.betting.enums.TimeInForce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LimitOrder {
    @NonNull private Double size;
    @NonNull private Double price;
    @NonNull private PersistenceType persistenceType;
    private TimeInForce timeInForce;
    private Double minFillSize;
    private BetTargetType betTargetType;
    private Double betTargetSize;
}

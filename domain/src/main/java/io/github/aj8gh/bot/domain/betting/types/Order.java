package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.OrderStatus;
import io.github.aj8gh.bot.domain.betting.enums.OrderType;
import io.github.aj8gh.bot.domain.betting.enums.PersistenceType;
import io.github.aj8gh.bot.domain.betting.enums.Side;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  @NonNull
  private String betId;
  @NonNull
  private OrderType orderType;
  @NonNull
  private OrderStatus status;
  @NonNull
  private PersistenceType persistenceType;
  @NonNull
  private Side side;
  @NonNull
  private Double price;
  @NonNull
  private Double size;
  @NonNull
  private Double bspLiability;
  @NonNull
  private Date placedDate;
  private Double avgPriceMatched;
  private Double sizeMatched;
  private Double sizeRemaining;
  private Double sizeLapsed;
  private Double sizeCancelled;
  private Double sizeVoided;
  private String customerOrderRef;
  private String customerStrategyRef;
}

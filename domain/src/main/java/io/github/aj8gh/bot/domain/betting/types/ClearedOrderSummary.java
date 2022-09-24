package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.OrderType;
import io.github.aj8gh.bot.domain.betting.enums.PersistenceType;
import io.github.aj8gh.bot.domain.betting.enums.Side;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClearedOrderSummary {

  private String eventTypeId;
  private String eventId;
  private String marketId;
  private Long selectionId;
  private Double handicap;
  private String betId;
  private Date placedDate;
  private PersistenceType persistenceType;
  private OrderType orderType;
  private Side side;
  private ItemDescription itemDescription;
  private String betOutcome;
  private Double priceRequested;
  private Date settledDate;
  private Date lastMatchedDate;
  private Integer betCount;
  private Double commission;
  private Double priceMatched;
  private Boolean priceReduced;
  private Double sizeSettled;
  private Double profit;
  private String customerOrderRef;
  private String customerStrategyRef;
}

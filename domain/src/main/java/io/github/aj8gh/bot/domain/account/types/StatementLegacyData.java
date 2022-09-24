package io.github.aj8gh.bot.domain.account.types;

import io.github.aj8gh.bot.domain.account.enums.WinLose;
import io.github.aj8gh.bot.domain.betting.enums.MarketType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatementLegacyData {

  private Double avgPrice;
  private Double betSize;
  private String betType;
  private String betCategoryType;
  private String commissionRate;
  private Long eventId;
  private Long eventTypeId;
  private String fullMarketName;
  private Double grossBetAmount;
  private String marketName;
  private MarketType marketType;
  private Date placedDate;
  private Long selectionId;
  private String selectionName;
  private Date startDate;
  private String transactionType;
  private Long transactionId;
  private WinLose winLose;
  private Double deadHeatPriceDivisor;
  private Double avgPriceRaw;
}

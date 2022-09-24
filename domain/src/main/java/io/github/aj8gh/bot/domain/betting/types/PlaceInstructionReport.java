package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.InstructionReportErrorCode;
import io.github.aj8gh.bot.domain.betting.enums.InstructionReportStatus;
import io.github.aj8gh.bot.domain.betting.enums.OrderStatus;
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
public class PlaceInstructionReport {

  @NonNull
  private InstructionReportStatus status;
  @NonNull
  private PlaceInstruction instruction;
  private InstructionReportErrorCode errorCode;
  private OrderStatus orderStatus;
  private String betId;
  private Date placedDate;
  private Double averagePriceMatched;
  private Double sizeMatched;
}

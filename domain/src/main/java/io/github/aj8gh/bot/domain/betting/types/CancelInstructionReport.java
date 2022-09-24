package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.InstructionReportErrorCode;
import io.github.aj8gh.bot.domain.betting.enums.InstructionReportStatus;
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
public class CancelInstructionReport {

  @NonNull
  private InstructionReportStatus status;
  @NonNull
  private Double sizeCancelled;
  private InstructionReportErrorCode errorCode;
  private CancelInstruction instruction;
  private Date cancelledDate;

}

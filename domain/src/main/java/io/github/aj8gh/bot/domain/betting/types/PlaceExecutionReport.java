package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.ExecutionReportErrorCode;
import io.github.aj8gh.bot.domain.betting.enums.ExecutionReportStatus;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceExecutionReport {

  @NonNull
  private ExecutionReportStatus status;
  private String customerRef;
  private ExecutionReportErrorCode errorCode;
  private String marketId;
  private List<PlaceInstructionReport> instructionReports;
}

package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.ExecutionReportErrorCode;
import io.github.aj8gh.bot.domain.betting.enums.ExecutionReportStatus;
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
public class ReplaceExecutionReport {

    private String customerRef;
    @NonNull
    private ExecutionReportStatus status;

    private ExecutionReportErrorCode errorCode;

    private String marketId;

    private List<ReplaceInstructionReport> instructionReports;
}
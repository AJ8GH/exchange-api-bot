package io.github.aj8gh.bot.domain.betting.types;

import io.github.aj8gh.bot.domain.betting.enums.InstructionReportErrorCode;
import io.github.aj8gh.bot.domain.betting.enums.InstructionReportStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelInstructionReport {
    @NonNull
    private InstructionReportStatus status;

    private InstructionReportErrorCode errorCode;

    private CancelInstruction instruction;
    @NonNull
    private Double sizeCancelled;

    private Date cancelledDate;

}

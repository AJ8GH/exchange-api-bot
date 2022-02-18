package jonasa.exchangeapibot.betting.types;

import jonasa.exchangeapibot.betting.enums.InstructionReportErrorCode;
import jonasa.exchangeapibot.betting.enums.InstructionReportStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInstructionReport {
    @NonNull
    private InstructionReportStatus status;

    private InstructionReportErrorCode errorCode;
    @NonNull
    private UpdateInstruction instruction;
}

package jonasa.exchangeapibot.betting.types;

import jonasa.exchangeapibot.betting.enums.InstructionReportErrorCode;
import jonasa.exchangeapibot.betting.enums.InstructionReportStatus;
import jonasa.exchangeapibot.betting.enums.OrderStatus;
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
public class PlaceInstructionReport {
    @NonNull
    private InstructionReportStatus status;

    private InstructionReportErrorCode errorCode;

    private OrderStatus orderStatus;
    @NonNull
    private PlaceInstruction instruction;

    private String betId;

    private Date placedDate;

    private Double averagePriceMatched;

    private Double sizeMatched;
}

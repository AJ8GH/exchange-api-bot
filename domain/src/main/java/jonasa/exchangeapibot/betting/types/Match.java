package jonasa.exchangeapibot.betting.types;

import jonasa.exchangeapibot.betting.enums.Side;
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
public class Match {

    private String betId;

    private String matchId;
    @NonNull
    private Side side;
    @NonNull
    private Double price;
    @NonNull
    private Double size;

    private Date matchDate;
}

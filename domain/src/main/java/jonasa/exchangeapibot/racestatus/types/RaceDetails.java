package jonasa.exchangeapibot.racestatus.types;

import jonasa.exchangeapibot.racestatus.enums.RaceStatus;
import jonasa.exchangeapibot.racestatus.enums.ResponseCode;
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
public class RaceDetails {
    @NonNull
    private String meetingId;
    @NonNull
    private String raceId;
    @NonNull
    private RaceStatus raceStatus;
    @NonNull
    private Date lastUpdated;
    @NonNull
    private Long sequence;
    @NonNull
    private ResponseCode responseCode;
}

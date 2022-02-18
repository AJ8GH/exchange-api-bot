package jonasa.exchangeapibot.betting.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketCatalogue {
    @NonNull
    private String marketId;
    @NonNull
    private String marketName;

    private Date marketStartTime;

    private MarketDescription description;

    private Double totalMatched;

    private List<RunnerCatalog> runners;

    private EventType eventType;

    private Competition competition;

    private Event event;
}

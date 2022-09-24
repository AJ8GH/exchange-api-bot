package io.github.aj8gh.bot.domain.betting.types;

import java.util.Date;
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
  private Competition competition = new Competition();
  private Event event;
}

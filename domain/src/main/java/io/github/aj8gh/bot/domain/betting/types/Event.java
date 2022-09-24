package io.github.aj8gh.bot.domain.betting.types;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

  private String id;
  private String name;
  private String countryCode;
  private String timezone;
  private String venue;
  private Date openDate;
}

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
public class TimeRange {

  private Date from;
  private Date to;
}

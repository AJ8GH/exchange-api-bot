package io.github.aj8gh.bot.domain.betting.types;

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
public class KeyLineDescription {

  @NonNull
  private List<KeyLineSelection> keyLine;
}

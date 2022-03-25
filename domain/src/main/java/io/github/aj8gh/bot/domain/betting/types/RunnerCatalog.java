package io.github.aj8gh.bot.domain.betting.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RunnerCatalog {
    @NonNull
    private Long selectionId;
    @NonNull
    private String runnerName;
    @NonNull
    private Double handicap;
    @NonNull
    private Integer sortPriority;

    private Map<String,String> metadata;
}

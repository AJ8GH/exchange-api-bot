package io.github.aj8gh.bot.domain.account.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperApp {
    @NonNull
    private String appName;
    @NonNull
    private Long appId;
    @NonNull
    private List<DeveloperAppVersion> appVersions;
}

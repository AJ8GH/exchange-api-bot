package io.github.aj8gh.bot.domain.accounts.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperAppVersion {
    @NonNull
    private String owner;
    @NonNull
    private Long versionId;
    @NonNull
    private String version;
    @NonNull
    private String applicationKey;
    @NonNull
    private Boolean delayData;
    @NonNull
    private Boolean subscriptionRequired;
    @NonNull
    private Boolean ownerManaged;
    @NonNull
    private Boolean active;

    private String vendorId;

    private String vendorSecret;
}

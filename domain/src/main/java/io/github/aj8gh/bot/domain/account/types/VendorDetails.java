package io.github.aj8gh.bot.domain.account.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorDetails {

  @NonNull
  private Long appVersionId;
  @NonNull
  private String vendorName;
  private String redirectUrl;
}

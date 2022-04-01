package io.github.aj8gh.bot.domain.account.types;

import io.github.aj8gh.bot.domain.account.enums.ItemClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatementItem {
    @NonNull private Date itemDate;
    private String refId;
    private Double amount;
    private Double balance;
    private ItemClass itemClass;
    private Map<String, String> itemClassData;
    private StatementLegacyData legacyData;
}

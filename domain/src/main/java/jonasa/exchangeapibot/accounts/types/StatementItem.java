package jonasa.exchangeapibot.accounts.types;

import jonasa.exchangeapibot.accounts.enums.ItemClass;
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

    private String refId;
    @NonNull
    private Date itemDate;

    private Double amount;

    private Double balance;

    private ItemClass itemClass;

    private Map<String, String> itemClassData;

    private StatementLegacyData legacyData;
}

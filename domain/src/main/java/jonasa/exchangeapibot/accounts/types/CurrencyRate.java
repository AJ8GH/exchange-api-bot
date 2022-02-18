package jonasa.exchangeapibot.accounts.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRate {

    private String currencyCode;

    private Double rate;
}

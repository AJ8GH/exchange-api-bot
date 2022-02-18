package jonasa.exchangeapibot.accounts.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponse {
    @NonNull
    private String transactionId;
}

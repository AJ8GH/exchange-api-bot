package jonasa.exchangeapibot.betting.types;

import jonasa.exchangeapibot.betting.enums.PersistenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInstruction {
    @NonNull
    private String betId;
    @NonNull
    private PersistenceType newPersistenceType;
}
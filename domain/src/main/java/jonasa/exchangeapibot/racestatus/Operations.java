package jonasa.exchangeapibot.racestatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operations {
    LIST_RACE_DETAILS("listRaceDetails");

    private final String value;
}

package jonasa.exchangeapibot.heartbeat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operations {
    HEARTBEAT("heartbeat");

    private final String value;
}

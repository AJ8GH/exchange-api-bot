package jonasa.exchangeapibot.console;

import jonasa.exchangeapibot.betting.client.BettingClient;
import jonasa.exchangeapibot.betting.types.EventTypeResult;
import jonasa.exchangeapibot.betting.types.MarketFilter;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class BettingConsole {
    private final BettingClient bettingClient;

    public BettingConsole(BettingClient bettingClient) {
        this.bettingClient = bettingClient;
    }

    @ShellMethod("Lists event types")
    public List<EventTypeResult> listEventTypes() {
        return bettingClient.listEventTypes(MarketFilter.builder().build());
    }
}

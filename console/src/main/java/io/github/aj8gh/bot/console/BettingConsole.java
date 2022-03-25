package io.github.aj8gh.bot.console;

import io.github.aj8gh.bot.betting.client.BettingClient;
import io.github.aj8gh.bot.domain.betting.types.EventTypeResult;
import io.github.aj8gh.bot.domain.betting.types.MarketFilter;
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

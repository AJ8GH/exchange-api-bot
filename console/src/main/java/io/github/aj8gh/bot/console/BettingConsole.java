package io.github.aj8gh.bot.console;

import io.github.aj8gh.bot.betting.client.BettingClient;
import io.github.aj8gh.bot.console.util.constants.Messages;
import io.github.aj8gh.bot.console.util.RequestManager;
import io.github.aj8gh.bot.console.util.ShellPrinter;
import io.github.aj8gh.bot.console.util.constants.Descriptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@Slf4j
@ShellComponent
public class BettingConsole {
    private final BettingClient bettingClient;
    private final ShellPrinter shell;
    private final RequestManager requestManager;

    public BettingConsole(BettingClient bettingClient,
                          ShellPrinter shell,
                          RequestManager requestManager) {
        this.bettingClient = bettingClient;
        this.shell = shell;
        this.requestManager = requestManager;
    }

    @ShellMethod(Descriptions.LIST_EVENT_TYPES)
    public void listEventTypes() {
        var request = requestManager.getMarketFilter();
        printResult(bettingClient.listEventTypes(request));
    }

    @ShellMethod(Descriptions.LIST_EVENTS)
    public void listEvents() {
        var request = requestManager.getMarketFilter();
        printResult(bettingClient.listEvents(request));
    }

    @ShellMethod(Descriptions.LIST_COMPETITIONS)
    public void listCompetitions() {
        var request = requestManager.getMarketFilter();
        printResult(bettingClient.listCompetitions(request));
    }

    @ShellMethod(Descriptions.LIST_MARKET_TYPES)
    public void listMarketTypes() {
        var request = requestManager.getMarketFilter();
        printResult(bettingClient.listMarketTypes(request));
    }

    @ShellMethod(Descriptions.LIST_COUNTRIES)
    public void listCountries() {
        var request = requestManager.getMarketFilter();
        printResult(bettingClient.listCountries(request));
    }

    @ShellMethod(Descriptions.LIST_VENUES)
    public void listVenues() {
        var request = requestManager.getMarketFilter();
        printResult(bettingClient.listVenues(request));
    }

    private <T> void printResult(List<T> result) {
        if (result.isEmpty()) {
            shell.warn(Messages.EMPTY_RESULT);
            return;
        }
        shell.print(result);
    }
}

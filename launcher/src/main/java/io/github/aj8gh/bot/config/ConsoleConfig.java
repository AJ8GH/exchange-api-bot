package io.github.aj8gh.bot.config;

import io.github.aj8gh.bot.auth.session.SessionSupplier;
import io.github.aj8gh.bot.betting.client.BettingClient;
import io.github.aj8gh.bot.console.AuthConsole;
import io.github.aj8gh.bot.console.BettingConsole;
import io.github.aj8gh.bot.console.RequestConsole;
import io.github.aj8gh.bot.console.util.BotPromptProvider;
import io.github.aj8gh.bot.console.util.MarketCache;
import io.github.aj8gh.bot.console.util.formatting.ColourFormatter;
import io.github.aj8gh.bot.console.util.RequestBuilder;
import io.github.aj8gh.bot.console.util.ShellPrinter;
import io.github.aj8gh.bot.console.util.formatting.TableFormatter;
import io.github.aj8gh.bot.console.util.formatting.TableHeaders;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ConsoleConfig {
    private final Terminal terminal;
    @Value("${shell.prompt}")
    private String prompt;
    @Value("${shell.out.info}")
    private String infoColour;
    @Value("${shell.out.success}")
    private String successColour;
    @Value("${shell.out.warning}")
    private String warningColour;
    @Value("${shell.out.error}")
    private String errorColour;
    @Value("${shell.view.width}")
    private int shellWidth;

    ConsoleConfig(Terminal terminal) {
        this.terminal = terminal;
    }

    @Bean
    AuthConsole authConsole(SessionSupplier sessionSupplier) {
        return new AuthConsole(sessionSupplier);
    }

    @Bean
    BettingConsole bettingConsole(BettingClient bettingClient) {
        return new BettingConsole(
                bettingClient, requestBuilder(), marketCache(), shellPrinter());
    }

    @Bean
    RequestConsole filterConsole() {
        return new RequestConsole(requestBuilder());
    }

    @Bean
    BotPromptProvider botPromptProvider() {
        return new BotPromptProvider(prompt);
    }

    @Bean
    ShellPrinter shellPrinter() {
        return new ShellPrinter(terminal, colourFormatter(), tableFormatter());
    }

    @Bean
    RequestBuilder requestBuilder() {
        return new RequestBuilder();
    }

    @Bean
    ColourFormatter colourFormatter() {
        var colourFormatter = new ColourFormatter();
        colourFormatter.setInfoColour(infoColour);
        colourFormatter.setSuccessColour(successColour);
        colourFormatter.setWarningColour(warningColour);
        colourFormatter.setErrorColour(errorColour);
        return colourFormatter;
    }

    @Bean
    TableFormatter tableFormatter() {
        return new TableFormatter(tableHeaders(), shellWidth);
    }

    @Bean
    TableHeaders tableHeaders() {
        return new TableHeaders();
    }

    @Bean
    MarketCache marketCache() {
        return new MarketCache();
    }
}

package io.github.aj8gh.bot.config;

import io.github.aj8gh.bot.auth.session.SessionSupplier;
import io.github.aj8gh.bot.betting.client.BettingClient;
import io.github.aj8gh.bot.console.AuthConsole;
import io.github.aj8gh.bot.console.BettingConsole;
import io.github.aj8gh.bot.console.BotConsole;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ConsoleConfig {

    @Bean
    BotConsole botConsole(ApplicationContext applicationContext) {
        return new BotConsole(applicationContext);
    }

    @Bean
    AuthConsole authConsole(SessionSupplier sessionSupplier) {
        return new AuthConsole(sessionSupplier);
    }

    @Bean
    BettingConsole bettingConsole(BettingClient bettingClient) {
        return new BettingConsole(bettingClient);
    }
}

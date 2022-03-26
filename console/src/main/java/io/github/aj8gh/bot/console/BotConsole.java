package io.github.aj8gh.bot.console;

import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Arrays;
import java.util.List;

@ShellComponent
public class BotConsole {
    private final ApplicationContext applicationContext;

    public BotConsole(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @ShellMethod("Returns the current environment")
    public List<String> env() {
        return Arrays.asList(applicationContext
                .getEnvironment()
                .getActiveProfiles());
    }
}

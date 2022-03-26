package io.github.aj8gh.bot.console;

import io.github.aj8gh.bot.auth.session.Session;
import io.github.aj8gh.bot.auth.session.SessionSupplier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.function.Supplier;

@ShellComponent
public class AuthConsole {
    private final Supplier<Session> sessionSupplier;

    public AuthConsole(SessionSupplier sessionSupplier) {
        this.sessionSupplier = sessionSupplier;
    }

    @ShellMethod("Authenticates a new session")
    public Session login() {
        return sessionSupplier.get();
    }
}

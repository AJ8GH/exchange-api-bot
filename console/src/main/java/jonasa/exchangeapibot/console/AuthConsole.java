package jonasa.exchangeapibot.console;

import jonasa.exchangeapibot.auth.session.Session;
import jonasa.exchangeapibot.auth.session.SessionSupplier;
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

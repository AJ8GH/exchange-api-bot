package jonasa.exchangeapibot.console;

import jonasa.exchangeapibot.auth.session.Session;
import jonasa.exchangeapibot.auth.session.SessionSupplier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AuthConsole {

    private final SessionSupplier sessionSupplier;

    public AuthConsole(SessionSupplier sessionSupplier) {
        this.sessionSupplier = sessionSupplier;
    }

    @ShellMethod("Authenticates a new session")
    public Session login() {
        return sessionSupplier.get();
    }

    @ShellMethod("Sets the max session duration")
    public void setSessionTtl(long ttl) {
        sessionSupplier.setSessionTtl(ttl);
    }

    @ShellMethod("Keeps the current session alive")
    public void setKeepAlive(long freq) {
        sessionSupplier.setKeepAliveFreq(freq);
    }
}

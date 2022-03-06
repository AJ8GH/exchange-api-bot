package jonasa.exchangeapibot.console;

import jonasa.exchangeapibot.auth.client.AuthClient;
import jonasa.exchangeapibot.auth.types.AuthResponse;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
public class AuthConsole {
    private static final String ERROR_MESSAGE = "Error Authenticating";

    private final AuthClient authClient;

    public AuthConsole(AuthClient authClient) {
        this.authClient = authClient;
    }

    @ShellMethod("Authenticates a new session")
    public String login() {
        Optional<AuthResponse> response = authClient.login();
        return response.isPresent() ? response.get().toString() : ERROR_MESSAGE;
    }
}

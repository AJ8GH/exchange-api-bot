package jonasa.exchangeapibot.console;

import jonasa.exchangeapibot.auth.client.AuthClient;
import jonasa.exchangeapibot.auth.types.AuthResponse;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellConsole {
    private final AuthClient authClient;

    public ShellConsole(AuthClient authClient) {
        this.authClient = authClient;
    }

    @ShellMethod("Says hello")
    public String hello() {
        return "Hello, World!";
    }

    @ShellMethod("Logs in")
    public String login() {
        AuthResponse response = authClient.login().get();
        return response.toString();
    }
}

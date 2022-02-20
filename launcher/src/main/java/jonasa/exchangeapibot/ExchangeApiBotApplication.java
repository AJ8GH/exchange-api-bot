package jonasa.exchangeapibot;

import jonasa.exchangeapibot.auth.client.AuthClient;
import jonasa.exchangeapibot.auth.types.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class ExchangeApiBotApplication {
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeApiBotApplication.class);

    public static void main(String[] args) {
        var context = SpringApplication.run(ExchangeApiBotApplication.class, args);

        AuthClient client = context.getBean(AuthClient.class);

        LOG.info("*** Authenticating ***");
        Optional<AuthResponse> response = client.login();
        LOG.info("*** Received response ***\n{}", response.get());
    }
}

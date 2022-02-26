package jonasa.exchangeapibot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ExchangeApiBotApplication {
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeApiBotApplication.class);
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        start(args);
    }

    public static void start(String[] args) {
        LOG.info("*** STARTING BOT ***");
        new Thread(() -> context = SpringApplication
                .run(ExchangeApiBotApplication.class, args))
                .start();
    }

    public static void stop() {
        if (context != null) {
            SpringApplication.exit(context);
        }
    }
}

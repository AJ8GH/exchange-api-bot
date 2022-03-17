package jonasa.exchangeapibot;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExchangeApiBotApplication {
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeApiBotApplication.class);

    @Generated
    public static void main(String[] args) {
        LOG.info("*** STARTING BOT ***");
        SpringApplication.run(ExchangeApiBotApplication.class, args);
    }
}

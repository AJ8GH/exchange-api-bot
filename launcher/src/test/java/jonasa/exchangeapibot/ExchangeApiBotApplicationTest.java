package jonasa.exchangeapibot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ExchangeApiBotApplicationTest {

    @Test
    void contextLoads() {
        ExchangeApiBotApplication.main(new String [] {});
        ExchangeApiBotApplication.stop();
    }
}

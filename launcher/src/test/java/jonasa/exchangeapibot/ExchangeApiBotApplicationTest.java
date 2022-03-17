package jonasa.exchangeapibot;

import jonasa.exchangeapibot.config.TestApplicationRunner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestApplicationRunner.class)
class ExchangeApiBotApplicationTest {

    @Test
    void contextLoads() {
    }
}

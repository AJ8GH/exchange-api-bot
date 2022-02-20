package jonasa.exchangeapibot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ExchangeApiBotApplicationTest {

    @Test
    void main_ContextLoads() {
        ExchangeApiBotApplication.main(new String[] {});
    }
}

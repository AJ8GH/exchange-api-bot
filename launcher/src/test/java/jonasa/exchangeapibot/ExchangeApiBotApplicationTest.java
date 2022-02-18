package jonasa.exchangeapibot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExchangeApiBotApplicationTest {

    @Test
    void main_ContextLoads() {
        ExchangeApiBotApplication.main(new String[] {});
    }
}

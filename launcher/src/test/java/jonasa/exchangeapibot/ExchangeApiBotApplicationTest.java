package jonasa.exchangeapibot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ExchangeApiBotApplicationTest {

    @Test
    void main_ContextLoads() {
        ExchangeApiBotApplication.main(new String[] {});
    }

    @Test
    void intentionallyFailingTest() {
        assertTrue(false);
    }
}

package jonasa.exchangeapibot.auth.session;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SessionTest {

    @Test
    void keepAlive() {
        var session = new Session("token", "product");
        assertEquals(session.lastUpdated(), session.getCreateTime());

        session.keepAlive();
        assertTrue(session.lastUpdated().isAfter(session.getCreateTime()));
    }
}

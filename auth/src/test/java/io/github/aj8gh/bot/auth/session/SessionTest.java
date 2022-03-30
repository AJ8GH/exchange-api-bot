package io.github.aj8gh.bot.auth.session;

import io.github.aj8gh.bot.auth.session.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SessionTest {

    @Test
    void keepAlive() {
        var session = new Session("token", "product");
        assertEquals(session.getUpdateTime(), session.getCreateTime());

        session.keepAlive();
        assertTrue(session.getUpdateTime().isAfter(session.getCreateTime()));
    }
}

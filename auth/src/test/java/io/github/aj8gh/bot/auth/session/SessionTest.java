package io.github.aj8gh.bot.auth.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SessionTest {

  @Test
  void keepAlive() {
    var session = new Session("token", "product");
    assertEquals(session.getUpdateTime(), session.getCreateTime());

    session.keepAlive();
    assertTrue(session.getUpdateTime().isAfter(session.getCreateTime()));
  }
}

package io.github.aj8gh.bot;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static io.github.aj8gh.bot.http.operations.AuthOperations.LOGIN;
import static io.github.aj8gh.bot.http.util.Headers.CONTENT_TYPE;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.aj8gh.bot.config.TestApplicationRunner;
import io.github.aj8gh.bot.domain.auth.enums.AuthStatus;
import io.github.aj8gh.bot.domain.auth.types.AuthResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestApplicationRunner.class)
@TestPropertySource(locations = "classpath:conf/test.properties")
class BotApplicationTest {

  private static final int PORT = 3000;
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final WireMockServer SERVER = new WireMockServer(PORT);

  @BeforeAll
  static void setUp() throws JsonProcessingException {
    var authResponse = AuthResponse.builder()
        .token("token")
        .status(AuthStatus.SUCCESS)
        .product("product")
        .build();

    SERVER.stubFor(post(urlPathEqualTo(LOGIN.path()))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
            .withBody(MAPPER.writeValueAsString(authResponse))));

    SERVER.start();
  }

  @AfterAll
  static void tearDown() {
    SERVER.stop();
  }

  @Test
  void contextLoads(ApplicationContext context) {
    assertNotNull(context);
  }
}

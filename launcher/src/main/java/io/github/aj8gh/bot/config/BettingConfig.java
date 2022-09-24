package io.github.aj8gh.bot.config;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
import static io.github.aj8gh.bot.http.util.Headers.ACCEPT;
import static io.github.aj8gh.bot.http.util.Headers.CONTENT_TYPE;
import static io.github.aj8gh.bot.http.util.Headers.X_APPLICATION;
import static io.github.aj8gh.bot.http.util.Headers.X_AUTHENTICATION;
import static io.github.aj8gh.bot.http.util.Headers.X_IP;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aj8gh.bot.auth.session.Session;
import io.github.aj8gh.bot.betting.client.BettingClient;
import io.github.aj8gh.bot.http.client.HttpClient;
import java.time.Duration;
import java.util.function.Supplier;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
class BettingConfig {

  private final Supplier<Session> sessionSupplier;
  @Value("${api.appKey}")
  private String appKey;
  @Value("${betting.rootUri}")
  private String rootUri;
  @Value("${request.timeout.read}")
  private long readTimeout;
  @Value("${request.timeout.connect}")
  private long connectTimeout;

  public BettingConfig(Supplier<Session> sessionSupplier) {
    this.sessionSupplier = sessionSupplier;
  }

  @Bean
  BettingClient bettingClient() {
    return new BettingClient(bettingHttpClient());
  }

  @Bean
  HttpClient bettingHttpClient() {
    return new HttpClient(bettingRestTemplate());
  }

  @Bean
  RestTemplate bettingRestTemplate() {
    return new RestTemplateBuilder()
        .additionalMessageConverters(bettingJacksonConverter())
        .rootUri(rootUri)
        .defaultHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
        .defaultHeader(ACCEPT.header(), ACCEPT.value())
        .defaultHeader(X_IP.header(), X_IP.value())
        .defaultHeader(X_APPLICATION.header(), appKey)
        .defaultHeader(X_AUTHENTICATION.header(), getSessionToken())
        .setConnectTimeout(Duration.ofMillis(connectTimeout))
        .setReadTimeout(Duration.ofMillis(readTimeout))
        .build();
  }

  @Bean
  MappingJackson2HttpMessageConverter bettingJacksonConverter() {
    var converter = new MappingJackson2HttpMessageConverter();
    converter.setObjectMapper(bettingObjectMapper());
    return converter;
  }

  @Bean
  ObjectMapper bettingObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper;
  }

  private String getSessionToken() {
    var session = sessionSupplier.get();
    if (session == null) {
      throw new BeanCreationException("Session is null");
    }
    return session.getToken();
  }
}

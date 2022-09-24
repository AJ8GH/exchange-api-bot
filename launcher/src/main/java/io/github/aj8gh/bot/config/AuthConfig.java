package io.github.aj8gh.bot.config;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
import static io.github.aj8gh.bot.http.operations.AuthOperations.LOGIN;
import static io.github.aj8gh.bot.http.util.Headers.ACCEPT;
import static io.github.aj8gh.bot.http.util.Headers.CONTENT_TYPE;
import static io.github.aj8gh.bot.http.util.Headers.X_APPLICATION;
import static io.github.aj8gh.bot.http.util.Headers.X_IP;
import static io.github.aj8gh.bot.http.util.Params.PASSWORD;
import static io.github.aj8gh.bot.http.util.Params.USERNAME;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aj8gh.bot.auth.client.AuthClient;
import io.github.aj8gh.bot.auth.session.SessionSupplier;
import io.github.aj8gh.bot.http.client.HttpClient;
import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Configuration
class AuthConfig {

  @Value("${api.username}")
  private String username;
  @Value("${api.password}")
  private String password;
  @Value("${api.appKey}")
  private String appKey;
  @Value("${auth.rootUri}")
  private String rootUri;
  @Value("${request.timeout.read}")
  private int readTimeout;
  @Value("${request.timeout.connect}")
  private int connectTimeout;
  @Value("${auth.session.ttl}")
  private int sessionTtl;
  @Value("${auth.session.keepAlive.frequency}")
  private int keepAliveFreq;

  @Bean
  SessionSupplier sessionSupplier() {
    var sessionSupplier = new SessionSupplier(
        authClient(), keepAliveScheduler(), Clock.systemUTC());
    sessionSupplier.setSessionTtl(sessionTtl);
    sessionSupplier.setKeepAliveFreq(keepAliveFreq);
    return sessionSupplier;
  }

  @Bean
  AuthClient authClient() {
    var loginQueryString = UriComponentsBuilder
        .fromUriString(LOGIN.path())
        .queryParam(USERNAME.param(), username)
        .queryParam(PASSWORD.param(), password)
        .toUriString();
    return new AuthClient(authHttpClient(), loginQueryString);
  }

  @Bean
  HttpClient authHttpClient() {
    return new HttpClient(authRestTemplate());
  }

  @Bean
  RestTemplate authRestTemplate() {
    return new RestTemplateBuilder()
        .additionalMessageConverters(authJacksonConverter())
        .rootUri(rootUri)
        .defaultHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
        .defaultHeader(ACCEPT.header(), ACCEPT.value())
        .defaultHeader(X_IP.header(), X_IP.value())
        .defaultHeader(X_APPLICATION.header(), appKey)
        .setConnectTimeout(Duration.ofMillis(connectTimeout))
        .setReadTimeout(Duration.ofMillis(readTimeout))
        .build();
  }

  @Bean
  MappingJackson2HttpMessageConverter authJacksonConverter() {
    var converter = new MappingJackson2HttpMessageConverter();
    converter.setObjectMapper(authObjectMapper());
    return converter;
  }

  @Bean
  ObjectMapper authObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper;
  }

  @Bean
  ScheduledExecutorService keepAliveScheduler() {
    return Executors.newSingleThreadScheduledExecutor();
  }
}

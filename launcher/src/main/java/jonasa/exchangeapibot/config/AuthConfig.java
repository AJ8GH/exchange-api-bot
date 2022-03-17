package jonasa.exchangeapibot.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jonasa.exchangeapibot.auth.client.AuthClient;
import jonasa.exchangeapibot.auth.session.SessionSupplier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static jonasa.exchangeapibot.auth.util.AuthOperations.LOGIN;
import static jonasa.exchangeapibot.auth.util.Headers.ACCEPT;
import static jonasa.exchangeapibot.auth.util.Headers.CONTENT_TYPE;
import static jonasa.exchangeapibot.auth.util.Headers.X_APPLICATION;
import static jonasa.exchangeapibot.auth.util.Headers.X_IP;

@Configuration
class AuthConfig {
    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";

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
                authClient(), keepAliveScheduler(), Clock.systemUTC()
        );
        sessionSupplier.setSessionTtl(sessionTtl);
        sessionSupplier.setKeepAliveFreq(keepAliveFreq);
        return sessionSupplier;
    }

    @Bean
    AuthClient authClient() {
        var loginQueryString = UriComponentsBuilder
                .fromUriString(LOGIN.path())
                .queryParam(USERNAME_PARAM, username)
                .queryParam(PASSWORD_PARAM, password)
                .toUriString();

        return new AuthClient(authRestTemplate(), loginQueryString);
    }

    @Bean
    RestTemplate authRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri(rootUri)
                .additionalMessageConverters(authJacksonConverter())
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
        mapper.configure(
                DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
                true
        );
        return mapper;
    }

    @Bean
    ScheduledExecutorService keepAliveScheduler() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}

package jonasa.exchangeapibot.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import jonasa.exchangeapibot.auth.session.SessionSupplier;
import jonasa.exchangeapibot.betting.client.BettingClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
import static jonasa.exchangeapibot.util.Headers.ACCEPT;
import static jonasa.exchangeapibot.util.Headers.CONTENT_TYPE;
import static jonasa.exchangeapibot.util.Headers.X_APPLICATION;
import static jonasa.exchangeapibot.util.Headers.X_AUTHENTICATION;
import static jonasa.exchangeapibot.util.Headers.X_IP;

@Configuration
class BettingConfig {

    @Value("${api.appKey}")
    private String appKey;
    @Value("${betting.rootUri}")
    private String rootUri;
    @Value("${request.timeout.read}")
    private long readTimeout;
    @Value("${request.timeout.connect}")
    private long connectTimeout;

    @Bean
    BettingClient bettingClient(SessionSupplier sessionSupplier) {
        return new BettingClient(bettingRestTemplate(sessionSupplier));
    }

    @Bean
    RestTemplate bettingRestTemplate(SessionSupplier sessionSupplier) {
        String token = sessionSupplier.get().getToken();
        return new RestTemplateBuilder()
                .additionalMessageConverters(bettingJacksonConverter())
                .rootUri(rootUri)
                .defaultHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
                .defaultHeader(ACCEPT.header(), ACCEPT.value())
                .defaultHeader(X_IP.header(), X_IP.value())
                .defaultHeader(X_APPLICATION.header(), appKey)
                .defaultHeader(X_AUTHENTICATION.header(), token)
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
}

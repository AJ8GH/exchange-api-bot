package jonasa.exchangeapibot.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jonasa.exchangeapibot.auth.client.AuthClient;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import static jonasa.exchangeapibot.auth.util.Headers.ACCEPT;
import static jonasa.exchangeapibot.auth.util.Headers.CONTENT_TYPE;
import static jonasa.exchangeapibot.auth.util.Headers.X_APPLICATION;
import static jonasa.exchangeapibot.auth.util.Headers.X_IP;

@Configuration
class AuthConfig {

    @Value("${api.username}")
    private String username;

    @Value("${api.password}")
    private String password;

    @Value("${api.appKey}")
    private String appKey;

    @Value("${login.baseUri}")
    private String baseUri;

    @Value("${request.timeout.read}")
    private int readTimeout;

    @Value("${request.timeout.connect}")
    private int connectTimeout;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .additionalMessageConverters(mappingJackson2HttpMessageConverter())
                .defaultHeader(CONTENT_TYPE.header(), CONTENT_TYPE.value())
                .defaultHeader(ACCEPT.header(), ACCEPT.value())
                .defaultHeader(X_IP.header(), X_IP.value())
                .defaultHeader(X_APPLICATION.header(), appKey)
                .setConnectTimeout(Duration.ofSeconds(connectTimeout))
                .setReadTimeout(Duration.ofSeconds(readTimeout))
                .build();
    }

    @Bean
    AuthClient authClient() {
        try {
            URI uri = new URI(baseUri);
            return new AuthClient(restTemplate(), uri, username, password);
        } catch (URISyntaxException e) {
            throw new BeanCreationException("Error creating URI");
        }
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return mapper;
    }
}

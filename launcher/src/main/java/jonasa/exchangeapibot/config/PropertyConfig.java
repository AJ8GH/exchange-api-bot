package jonasa.exchangeapibot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@PropertySources({
        @PropertySource(value = "classpath:conf/overrides.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:conf/api-nxt-secret.properties", ignoreResourceNotFound = true),
})
@Configuration
class PropertyConfig {
    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

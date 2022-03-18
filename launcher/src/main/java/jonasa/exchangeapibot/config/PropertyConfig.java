package jonasa.exchangeapibot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@PropertySource(value = "classpath:conf/overrides.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:conf/jmx.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:conf/${spring.profiles.active}-secret.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:conf/${spring.profiles.active}.properties", ignoreResourceNotFound = true)
@Configuration
class PropertyConfig {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

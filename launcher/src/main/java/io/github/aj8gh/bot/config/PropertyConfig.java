package io.github.aj8gh.bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@PropertySource(value = "classpath:conf/defaults.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:conf/overrides.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:conf/${spring.profiles.active}.properties", ignoreResourceNotFound = true)
@Configuration
class PropertyConfig {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

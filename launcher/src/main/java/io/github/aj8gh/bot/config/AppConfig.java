package io.github.aj8gh.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
class AppConfig {

    @Bean
    @Primary
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

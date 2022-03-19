package jonasa.exchangeapibot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdmk.comm.HtmlAdaptorServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JmxHtmlConfig {

    @Value("${jmx.html.port}")
    private int jmxHtmlPort;

    @Bean(initMethod = "start")
    HtmlAdaptorServer htmlAdaptor() {
        return new HtmlAdaptorServer(jmxHtmlPort);
    }

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

package jonasa.exchangeapibot.config;

import com.sun.jdmk.comm.HtmlAdaptorServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmxHtmlConfig {

    @Value("${jmx.html.port}")
    private int jmxHtmlPort;

    @Bean(initMethod = "start")
    public HtmlAdaptorServer htmlAdaptor() {
        return new HtmlAdaptorServer(jmxHtmlPort);
    }
}

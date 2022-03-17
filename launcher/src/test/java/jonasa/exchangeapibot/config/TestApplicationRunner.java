package jonasa.exchangeapibot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellRunner;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration
@ActiveProfiles("test")
public class TestApplicationRunner implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TestApplicationRunner.class);

    @MockBean
    InteractiveShellRunner interactiveShellRunner;

    public TestApplicationRunner() {
        LOG.info("*** Test Application Runner started! ***");
    }

    @Override
    public void run(ApplicationArguments args) {
    }
}

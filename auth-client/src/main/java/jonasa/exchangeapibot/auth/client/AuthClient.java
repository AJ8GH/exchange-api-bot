package jonasa.exchangeapibot.auth.client;

import jonasa.exchangeapibot.auth.types.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import static jonasa.exchangeapibot.auth.util.AuthOperations.LOGIN;

public class AuthClient {
    private static final Logger LOG = LoggerFactory.getLogger(AuthClient.class);

    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";

    private final RestTemplate restTemplate;
    private final URI baseUri;
    private final String username;
    private final String password;

    private URI authUri;
    private int port;

    public AuthClient(RestTemplate restTemplate, URI baseUri,
                      String username, String password) {
        this.restTemplate = restTemplate;
        this.baseUri = baseUri;
        this.username = username;
        this.password = password;
        buildAuthUri();
    }

    public Optional<AuthResponse> login() {
        try {
            var response = restTemplate.postForObject(authUri, null, AuthResponse.class);
            LOG.info("Auth response in client: {}", response);
            return Optional.ofNullable(response);

        } catch (RestClientResponseException e) {
            LOG.error("Error calling {} with status {}, {}", authUri, e.getRawStatusCode(), e.getMessage());
        } catch (Exception e) {
            LOG.error("Error calling {}, {}", authUri, e.getMessage());
        }
        return Optional.empty();
    }

    public void setPort(int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Port not in allowed range");
        }
        this.port = port;
        buildAuthUri();
    }

    private void buildAuthUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(baseUri);
        if (port != 0) builder.port(port);
        this.authUri = builder.path(LOGIN.path())
                .queryParam(USERNAME_PARAM, username)
                .queryParam(PASSWORD_PARAM, password)
                .build()
                .toUri();
    }
}

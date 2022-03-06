package jonasa.exchangeapibot.auth.client;

import jonasa.exchangeapibot.auth.types.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

import static jonasa.exchangeapibot.auth.util.AuthOperations.KEEP_ALIVE;
import static jonasa.exchangeapibot.auth.util.AuthOperations.LOGIN;
import static jonasa.exchangeapibot.auth.util.Headers.X_AUTHENTICATION;

public class AuthClient {
    private static final Logger LOG = LoggerFactory.getLogger(AuthClient.class);

    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";

    private final RestTemplate restTemplate;
    private final URI baseUri;
    private final String username;
    private final String password;

    public AuthClient(RestTemplate restTemplate, URI baseUri,
                      String username, String password) {
        this.restTemplate = restTemplate;
        this.baseUri = baseUri;
        this.username = username;
        this.password = password;
    }

    public Optional<AuthResponse> login() {
        var uri = UriComponentsBuilder.fromUri(baseUri)
                .path(LOGIN.path())
                .queryParam(USERNAME_PARAM, username)
                .queryParam(PASSWORD_PARAM, password)
                .build()
                .toUri();
        return makeRequest(uri, null);
    }

    public Optional<AuthResponse> keepAlive(String token) {
        var headers = new HttpHeaders();
        headers.add(X_AUTHENTICATION.header(), token);
        return makeRequest(baseUri.resolve(KEEP_ALIVE.path()), headers);
    }

    private Optional<AuthResponse> makeRequest(URI uri, HttpHeaders headers) {
        try {
            var response = restTemplate.postForObject(uri, headers, AuthResponse.class);
            if (Objects.requireNonNull(response).getError() == null) {
                return Optional.of(response);
            }
            LOG.error("Auth error from {}, {}", uri, response.getError());
        } catch (RestClientResponseException e) {
            LOG.error("Rest exception from {}, {}, {}", uri, e.getRawStatusCode(), e.getMessage());
        } catch (Exception e) {
            LOG.error("Exception from {}, {}", uri, e.getMessage());
        }
        return Optional.empty();
    }
}

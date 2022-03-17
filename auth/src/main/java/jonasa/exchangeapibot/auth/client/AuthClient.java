package jonasa.exchangeapibot.auth.client;

import jonasa.exchangeapibot.auth.types.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

import static jonasa.exchangeapibot.auth.util.AuthOperations.KEEP_ALIVE;
import static jonasa.exchangeapibot.auth.util.AuthOperations.LOGOUT;
import static jonasa.exchangeapibot.auth.util.Headers.X_AUTHENTICATION;

public class AuthClient {
    private static final Logger LOG = LoggerFactory.getLogger(AuthClient.class);

    private final RestTemplate restTemplate;
    private final String loginQuery;

    public AuthClient(RestTemplate restTemplate, String loginQuery) {
        this.restTemplate = restTemplate;
        this.loginQuery = loginQuery;
    }

    public Optional<AuthResponse> login() {
        return sendRequest(loginQuery, null);
    }

    public Optional<AuthResponse> logout(String token) {
        return sendRequest(LOGOUT.path(), buildAuthenticatedRequest(token));
    }

    public Optional<AuthResponse> keepAlive(String token) {
        return sendRequest(KEEP_ALIVE.path(), buildAuthenticatedRequest(token));
    }

    private Optional<AuthResponse> sendRequest(String uri, HttpEntity<Void> request) {
        try {
            var response = restTemplate.postForObject(uri, request, AuthResponse.class);
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

    private HttpEntity<Void> buildAuthenticatedRequest(String token) {
        var headers = new HttpHeaders();
        headers.add(X_AUTHENTICATION.header(), token);
        return  new HttpEntity<>(headers);
    }
}

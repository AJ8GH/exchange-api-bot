package io.github.aj8gh.bot.auth.client;

import io.github.aj8gh.bot.domain.auth.types.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

import static io.github.aj8gh.bot.auth.util.AuthOperations.KEEP_ALIVE;
import static io.github.aj8gh.bot.auth.util.AuthOperations.LOGIN;
import static io.github.aj8gh.bot.auth.util.AuthOperations.LOGOUT;
import static io.github.aj8gh.bot.domain.util.Headers.X_AUTHENTICATION;

public class AuthClient {
    private static final Logger LOG = LoggerFactory.getLogger(AuthClient.class);

    private final RestTemplate restTemplate;
    private final String loginQuery;

    public AuthClient(RestTemplate restTemplate, String loginQuery) {
        this.restTemplate = restTemplate;
        this.loginQuery = loginQuery;
    }

    public Optional<AuthResponse> login() {
        LOG.info("*** Authenticating ***");
        return sendRequest(loginQuery, null);
    }

    public Optional<AuthResponse> logout(String token) {
        return sendRequest(LOGOUT.path(), buildRequest(token));
    }

    public Optional<AuthResponse> keepAlive(String token) {
        return sendRequest(KEEP_ALIVE.path(), buildRequest(token));
    }

    private Optional<AuthResponse> sendRequest(String path, HttpEntity<Void> request) {
        try {
            var response = restTemplate.postForObject(path, request, AuthResponse.class);
            if (Objects.requireNonNull(response).getError() == null) {
                var logPath = path.contains("login") ? LOGIN.path() : path;
                LOG.info("{}: {}", logPath, response.getStatus());
                return Optional.of(response);
            }
            LOG.error("{}: {}, {}", path, response.getStatus(), response.getError());
        } catch (RestClientResponseException e) {
            LOG.error("{}: {}, {}", path, e.getRawStatusCode(), e.getMessage());
        } catch (Exception e) {
            LOG.error("{}: {}", path, e.getMessage());
        }
        return Optional.empty();
    }

    private HttpEntity<Void> buildRequest(String token) {
        var headers = new HttpHeaders();
        headers.add(X_AUTHENTICATION.header(), token);
        return new HttpEntity<>(headers);
    }
}

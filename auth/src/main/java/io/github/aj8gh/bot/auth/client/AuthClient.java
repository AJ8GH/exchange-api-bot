package io.github.aj8gh.bot.auth.client;

import io.github.aj8gh.bot.domain.auth.types.AuthResponse;
import io.github.aj8gh.bot.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.Optional;

import static io.github.aj8gh.bot.http.client.Headers.X_AUTHENTICATION;
import static io.github.aj8gh.bot.http.operations.AuthOperations.*;

public class AuthClient {
    private static final Logger LOG = LoggerFactory.getLogger(AuthClient.class);

    private final HttpClient httpClient;
    private final String loginQuery;

    public AuthClient(HttpClient httpClient, String loginQuery) {
        this.httpClient = httpClient;
        this.loginQuery = loginQuery;
    }

    public Optional<AuthResponse> login() {
        LOG.info("*** Authenticating ***");
        return sendRequest(loginQuery, null);
    }

    public Optional<AuthResponse> logout(String token) {
        LOG.info("*** Logging out ***");
        return sendRequest(LOGOUT.path(), buildRequest(token));
    }

    public Optional<AuthResponse> keepAlive(String token) {
        LOG.info("*** Sending keep-alive ***");
        return sendRequest(KEEP_ALIVE.path(), buildRequest(token));
    }

    private Optional<AuthResponse> sendRequest(String path,
                                               HttpEntity<Void> request) {
        return httpClient.post(path, request, AuthResponse.class)
                .map(response -> {
                    if (response.getError() == null) return response;
                    logResponseError(path, response);
                    return null;
                });
    }

    private HttpEntity<Void> buildRequest(String token) {
        var headers = new HttpHeaders();
        headers.add(X_AUTHENTICATION.header(), token);
        return new HttpEntity<>(headers);
    }

    private void logResponseError(String path, AuthResponse response) {
        var message = response.getError().message();
        LOG.info("{}: {} {} {}",
                path.contains(LOGIN.path()) ? LOGIN.path() : path,
                response.getStatus(),
                response.getError(),
                message);
    }
}

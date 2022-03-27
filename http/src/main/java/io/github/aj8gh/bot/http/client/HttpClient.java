package io.github.aj8gh.bot.http.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class HttpClient {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClient.class);
    private final RestTemplate restTemplate;

    public HttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> Optional<T> post(String path,
                                HttpEntity<?> request,
                                Class<T> type) {
        try {
            var response = restTemplate.postForObject(path, request, type);
            return Optional.ofNullable(response);
        } catch (RestClientResponseException e) {
            LOG.error("{}: {}, {}", path, e.getRawStatusCode(), e.getMessage());
        } catch (Exception e) {
            LOG.error("{}: {}", path, e.getMessage());
        }
        return Optional.empty();
    }
}

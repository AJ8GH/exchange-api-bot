package io.github.aj8gh.bot.auth.session;

import io.github.aj8gh.bot.auth.client.AuthClient;
import io.github.aj8gh.bot.domain.auth.enums.AuthStatus;
import io.github.aj8gh.bot.domain.auth.types.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static io.github.aj8gh.bot.domain.auth.enums.AuthStatus.SUCCESS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class SessionSupplierTest {
    private static final String INITIAL_TOKEN = "initialToken";
    private static final String NEW_TOKEN = "newToken";
    private static final long SESSION_TTL = 1000;
    private static final long KEEP_ALIVE_FREQ = 100;

    private SessionSupplier sessionSupplier;
    @Mock
    private AuthClient authClient;
    @Mock
    private Clock clock;
    @Mock
    private ScheduledExecutorService scheduler;

    @BeforeEach
    void setUp() {
        openMocks(this);
        sessionSupplier = new SessionSupplier(authClient, scheduler, clock);
        sessionSupplier.setSessionTtl(SESSION_TTL);
        sessionSupplier.setKeepAliveFreq(KEEP_ALIVE_FREQ);
    }

    @Test
    void get_SessionActive_ReturnsExistingSession() {
        when(authClient.login()).thenReturn(Optional.of(AuthResponse.builder()
                .token(INITIAL_TOKEN)
                .build()));
        when(clock.instant()).thenReturn(Instant.now());

        sessionSupplier.get();

        when(authClient.login()).thenReturn(Optional.of(AuthResponse.builder()
                .token(NEW_TOKEN)
                .build()));

        var session = sessionSupplier.get();

        verify(authClient, times(1)).login();
        verifyNoMoreInteractions(authClient);
        assertEquals(INITIAL_TOKEN, session.getToken());
    }

    @Test
    void get_SessionExpired_ReturnsNewToken() {
        when(authClient.login()).thenReturn(Optional.of(AuthResponse.builder()
                .token(INITIAL_TOKEN)
                .build()));

        when(clock.instant()).thenReturn(Instant.ofEpochMilli(Instant.now()
                .toEpochMilli() + SESSION_TTL * 2));

        sessionSupplier.get();

        when(authClient.login()).thenReturn(Optional.of(AuthResponse.builder()
                .token(NEW_TOKEN)
                .build()));

        var token = sessionSupplier.get();

        verify(authClient, times(2)).login();
        assertEquals(NEW_TOKEN, token.getToken());
    }

    @Test
    void get_Fail_ReturnsNull() {
        when(authClient.login()).thenReturn(Optional.empty());

        var token = sessionSupplier.get();

        verify(authClient).login();
        assertNull(token);
    }

    @Test
    void get_OnLogin_SchedulesKeepAliveJob() {
        when(authClient.login()).thenReturn(Optional.of(AuthResponse.builder()
                .token(INITIAL_TOKEN)
                .build()));

        when(clock.instant()).thenReturn(Instant.ofEpochMilli(Instant.now()
                .toEpochMilli() + SESSION_TTL));

        sessionSupplier.get();

        verify(scheduler).scheduleAtFixedRate(
                any(Runnable.class),
                eq(KEEP_ALIVE_FREQ),
                eq(KEEP_ALIVE_FREQ),
                eq(TimeUnit.MILLISECONDS));
    }

    @Test
    void get_KeepAliveFrequencyReached_SessionKeptAlive() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        sessionSupplier = new SessionSupplier(authClient, scheduler, clock);
        sessionSupplier.setKeepAliveFreq(KEEP_ALIVE_FREQ);

        when(authClient.login()).thenReturn(Optional.of(AuthResponse.builder()
                .token(INITIAL_TOKEN)
                .build()));
        when(clock.instant()).thenReturn(Instant.ofEpochMilli(Instant.now()
                .toEpochMilli() + KEEP_ALIVE_FREQ));

        sessionSupplier.get();

        var invokeTime = Instant.now();
        await().until(() -> Instant.now()
                .isAfter(invokeTime.plusMillis(KEEP_ALIVE_FREQ)));

        verify(authClient).keepAlive(INITIAL_TOKEN);
    }
}

package com.example.retrylab;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryWorker {
    private final AtomicInteger attempts = new AtomicInteger();

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 0), include = IllegalStateException.class)
    public String attempt(String paymentId) {
        int current = attempts.incrementAndGet();
        if (current < 3) throw new IllegalStateException("temporary failure: " + paymentId);
        return "charged:" + paymentId;
    }

    public int attempts() { return attempts.get(); }
}

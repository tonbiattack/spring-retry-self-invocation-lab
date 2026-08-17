package com.example.retrylab;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final AtomicInteger attempts = new AtomicInteger();

    public String charge(String paymentId) {
        return attempt(paymentId);
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 0), include = IllegalStateException.class)
    String attempt(String paymentId) {
        int current = attempts.incrementAndGet();
        if (current < 3) {
            throw new IllegalStateException("temporary failure: " + paymentId);
        }
        return "charged:" + paymentId;
    }

    public int attempts() {
        return attempts.get();
    }
}

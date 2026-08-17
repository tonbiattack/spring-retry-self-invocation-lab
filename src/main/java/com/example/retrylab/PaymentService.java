package com.example.retrylab;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final RetryWorker worker;

    public PaymentService(RetryWorker worker) { this.worker = worker; }

    public String charge(String paymentId) { return worker.attempt(paymentId); }

    public int attempts() { return worker.attempts(); }
}

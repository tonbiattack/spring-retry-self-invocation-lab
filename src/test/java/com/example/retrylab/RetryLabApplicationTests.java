package com.example.retrylab;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RetryLabApplicationTests {
    @Autowired
    PaymentService service;

    @Test
    void temporary_failure_is_retried_until_the_third_attempt() {
        assertThat(service.charge("payment-1")).isEqualTo("charged:payment-1");
        assertThat(service.attempts()).isEqualTo(3);
    }
}

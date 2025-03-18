package com.example.tasky.application.port.out.external;

import com.example.tasky.domain.model.User;

import java.math.BigDecimal;

public interface ExternalApiPort {
    void callPaymentService(User user, BigDecimal amount);
}

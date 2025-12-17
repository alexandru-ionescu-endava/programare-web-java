package com.example.model;

import java.util.Arrays;

public enum PaymentStatus {
    NEW,
    PROCESSED,
    CANCELLED;

    public static PaymentStatus getValueByString(String paymentStatus) {
        return Arrays.stream(PaymentStatus.values())
                .filter(p -> p.name().equals(paymentStatus))
                .findAny()
                .orElse(null);
    }
}

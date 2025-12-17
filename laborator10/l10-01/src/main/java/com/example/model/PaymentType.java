package com.example.model;

import java.util.Arrays;

public enum PaymentType {
    ONLINE,
    POS;


    public static PaymentType getValueByString(String paymentType) {
        return Arrays.stream(PaymentType.values())
                .filter(p -> p.name().equals(paymentType))
                .findFirst()
                .orElse(null);

    }
}
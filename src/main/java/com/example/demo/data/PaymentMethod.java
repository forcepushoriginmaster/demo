package com.example.demo.data;

public enum PaymentMethod {

    SHOP("Shop"),
    ONLINE("Online");

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}

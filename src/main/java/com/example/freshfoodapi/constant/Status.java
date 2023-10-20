package com.example.freshfoodapi.constant;

public enum Status {
    WAITING(0),
    CANCEL(1),
    COMPLETED(2);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

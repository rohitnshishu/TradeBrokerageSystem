package com.example.demo.constant;

public enum TRADESTATUS {
    INVALID("INVALID"),

    ACCEPTED("ACCEPTED"),

    REJECTED("REJECTED");

    private String value;

    TRADESTATUS(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}

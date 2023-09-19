package com.example.freshfoodapi.constant;

public enum ErrorCode {
    PARAM_INVALID("201");
    public String code;

    ErrorCode(String code) {
        this.code = code;
    }
}

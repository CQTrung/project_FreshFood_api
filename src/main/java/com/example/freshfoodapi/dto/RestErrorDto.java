package com.example.freshfoodapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestErrorDto<C> {
    private String errorCode;
    private String message;
    private Object data;

    public RestErrorDto() {
    }

    public RestErrorDto(String errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }
}

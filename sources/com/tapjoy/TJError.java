package com.tapjoy;

public class TJError {
    public int code;
    public String message;

    public TJError(int errorCode, String errorMessage) {
        this.code = errorCode;
        this.message = errorMessage;
    }
}

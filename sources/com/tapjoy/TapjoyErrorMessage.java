package com.tapjoy;

public class TapjoyErrorMessage {
    /* renamed from: a */
    private ErrorType f7088a;
    /* renamed from: b */
    private String f7089b;

    public enum ErrorType {
        INTERNAL_ERROR,
        SDK_ERROR,
        SERVER_ERROR,
        INTEGRATION_ERROR,
        NETWORK_ERROR
    }

    public TapjoyErrorMessage(ErrorType type, String message) {
        this.f7088a = type;
        this.f7089b = message;
    }

    public ErrorType getType() {
        return this.f7088a;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Type=" + this.f7088a.toString());
        stringBuilder.append(";Message=" + this.f7089b);
        return stringBuilder.toString();
    }
}

package com.facebook.places.internal;

public class ScannerException extends Exception {
    public Type type;

    public enum Type {
        NOT_SUPPORTED,
        PERMISSION_DENIED,
        DISABLED,
        SCAN_ALREADY_IN_PROGRESS,
        UNKNOWN_ERROR,
        TIMEOUT
    }

    public ScannerException(Type type) {
        super("Type: " + type.name());
        this.type = type;
    }

    public ScannerException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public ScannerException(Type type, Exception ex) {
        super("Type: " + type.name(), ex);
        this.type = type;
    }
}

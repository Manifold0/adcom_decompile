// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.internal;

public class ScannerException extends Exception
{
    public Type type;
    
    public ScannerException(final Type type) {
        super("Type: " + type.name());
        this.type = type;
    }
    
    public ScannerException(final Type type, final Exception ex) {
        super("Type: " + type.name(), ex);
        this.type = type;
    }
    
    public ScannerException(final Type type, final String s) {
        super(s);
        this.type = type;
    }
    
    public enum Type
    {
        DISABLED, 
        NOT_SUPPORTED, 
        PERMISSION_DENIED, 
        SCAN_ALREADY_IN_PROGRESS, 
        TIMEOUT, 
        UNKNOWN_ERROR;
    }
}

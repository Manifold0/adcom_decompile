// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch.exception;

public final class NotUsableException extends RuntimeException
{
    private final int errorCode;
    
    public NotUsableException(final String s, final int errorCode) {
        super(s);
        this.errorCode = errorCode;
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
}

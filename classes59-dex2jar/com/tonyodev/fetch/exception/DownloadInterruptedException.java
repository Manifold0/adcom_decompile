// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch.exception;

public class DownloadInterruptedException extends RuntimeException
{
    private int errorCode;
    
    public DownloadInterruptedException(final String s, final int errorCode) {
        super(s);
        this.errorCode = errorCode;
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
}

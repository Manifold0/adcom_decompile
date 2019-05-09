// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

public final class zzal extends Exception
{
    private final int errorCode;
    
    public zzal(final int errorCode, final String s) {
        super(s);
        this.errorCode = errorCode;
    }
    
    public final int getErrorCode() {
        return this.errorCode;
    }
}

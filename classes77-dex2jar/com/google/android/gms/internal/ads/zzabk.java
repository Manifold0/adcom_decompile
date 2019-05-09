// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzabk extends Exception
{
    private final int mErrorCode;
    
    public zzabk(final String s, final int mErrorCode) {
        super(s);
        this.mErrorCode = mErrorCode;
    }
    
    public final int getErrorCode() {
        return this.mErrorCode;
    }
}

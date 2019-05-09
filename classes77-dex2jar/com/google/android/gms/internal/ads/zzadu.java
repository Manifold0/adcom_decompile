// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
@VisibleForTesting
final class zzadu extends Exception
{
    private final int mErrorCode;
    
    public zzadu(final String s, final int mErrorCode) {
        super(s);
        this.mErrorCode = mErrorCode;
    }
    
    public final int getErrorCode() {
        return this.mErrorCode;
    }
}

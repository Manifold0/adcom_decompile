// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.Feature;

public final class UnsupportedApiCallException extends UnsupportedOperationException
{
    private final Feature zzas;
    
    @KeepForSdk
    public UnsupportedApiCallException(final Feature zzas) {
        this.zzas = zzas;
    }
    
    @Override
    public final String getMessage() {
        final String value = String.valueOf(this.zzas);
        return new StringBuilder(String.valueOf(value).length() + 8).append("Missing ").append(value).toString();
    }
}

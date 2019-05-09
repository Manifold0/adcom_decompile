// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class Response<T extends Result>
{
    private T zzap;
    
    public Response() {
    }
    
    protected Response(@NonNull final T zzap) {
        this.zzap = zzap;
    }
    
    @NonNull
    protected T getResult() {
        return this.zzap;
    }
    
    public void setResult(@NonNull final T zzap) {
        this.zzap = zzap;
    }
}

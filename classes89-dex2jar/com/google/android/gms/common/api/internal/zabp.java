// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Result;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Api;

public final class zabp<O extends Api.ApiOptions> extends zaag
{
    private final GoogleApi<O> zajh;
    
    public zabp(final GoogleApi<O> zajh) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.zajh = zajh;
    }
    
    @Override
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull final T t) {
        return this.zajh.doRead(t);
    }
    
    @Override
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull final T t) {
        return this.zajh.doWrite(t);
    }
    
    @Override
    public final Context getContext() {
        return this.zajh.getApplicationContext();
    }
    
    @Override
    public final Looper getLooper() {
        return this.zajh.getLooper();
    }
    
    @Override
    public final void zaa(final zacm zacm) {
    }
    
    @Override
    public final void zab(final zacm zacm) {
    }
}

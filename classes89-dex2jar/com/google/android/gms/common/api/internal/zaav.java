// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.google.android.gms.common.api.Result;
import java.util.Iterator;
import java.util.Collections;
import com.google.android.gms.common.api.Api;

public final class zaav implements zabd
{
    private final zabe zaft;
    
    public zaav(final zabe zaft) {
        this.zaft = zaft;
    }
    
    @Override
    public final void begin() {
        final Iterator<Api.Client> iterator = this.zaft.zagz.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().disconnect();
        }
        this.zaft.zaee.zaha = Collections.emptySet();
    }
    
    @Override
    public final void connect() {
        this.zaft.zaaz();
    }
    
    @Override
    public final boolean disconnect() {
        return true;
    }
    
    @Override
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(final T t) {
        this.zaft.zaee.zafc.add(t);
        return t;
    }
    
    @Override
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(final T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
    
    @Override
    public final void onConnected(final Bundle bundle) {
    }
    
    @Override
    public final void onConnectionSuspended(final int n) {
    }
    
    @Override
    public final void zaa(final ConnectionResult connectionResult, final Api<?> api, final boolean b) {
    }
}

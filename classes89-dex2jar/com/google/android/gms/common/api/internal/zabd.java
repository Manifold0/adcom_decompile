// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Api;

public interface zabd
{
    void begin();
    
    void connect();
    
    boolean disconnect();
    
     <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(final T p0);
    
     <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(final T p0);
    
    void onConnected(final Bundle p0);
    
    void onConnectionSuspended(final int p0);
    
    void zaa(final ConnectionResult p0, final Api<?> p1, final boolean p2);
}

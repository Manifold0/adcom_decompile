// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Api;
import android.support.annotation.NonNull;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.ConnectionResult;

public interface zabs
{
    ConnectionResult blockingConnect();
    
    ConnectionResult blockingConnect(final long p0, final TimeUnit p1);
    
    void connect();
    
    void disconnect();
    
    void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
    
     <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull final T p0);
    
     <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull final T p0);
    
    @Nullable
    ConnectionResult getConnectionResult(@NonNull final Api<?> p0);
    
    boolean isConnected();
    
    boolean isConnecting();
    
    boolean maybeSignIn(final SignInConnectionListener p0);
    
    void maybeSignOut();
    
    void zaw();
}

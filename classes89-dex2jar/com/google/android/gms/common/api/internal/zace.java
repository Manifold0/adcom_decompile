// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.ConnectionResult;
import android.util.Log;
import com.google.android.gms.signin.internal.zaj;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.WorkerThread;
import android.support.annotation.NonNull;
import com.google.android.gms.signin.zaa;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import android.os.Handler;
import android.content.Context;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.signin.internal.zac;

public final class zace extends zac implements ConnectionCallbacks, OnConnectionFailedListener
{
    private static Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zaki;
    private final Context mContext;
    private final Handler mHandler;
    private Set<Scope> mScopes;
    private final Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zaau;
    private ClientSettings zaet;
    private com.google.android.gms.signin.zad zagb;
    private zach zakj;
    
    static {
        zace.zaki = zaa.zaph;
    }
    
    @WorkerThread
    public zace(final Context context, final Handler handler, @NonNull final ClientSettings clientSettings) {
        this(context, handler, clientSettings, zace.zaki);
    }
    
    @WorkerThread
    public zace(final Context mContext, final Handler mHandler, @NonNull final ClientSettings clientSettings, final Api.AbstractClientBuilder<? extends com.google.android.gms.signin.zad, SignInOptions> zaau) {
        this.mContext = mContext;
        this.mHandler = mHandler;
        this.zaet = (ClientSettings)Preconditions.checkNotNull((Object)clientSettings, (Object)"ClientSettings must not be null");
        this.mScopes = clientSettings.getRequiredScopes();
        this.zaau = zaau;
    }
    
    @WorkerThread
    private final void zac(final zaj zaj) {
        final ConnectionResult connectionResult = zaj.getConnectionResult();
        if (connectionResult.isSuccess()) {
            final ResolveAccountResponse zacx = zaj.zacx();
            final ConnectionResult connectionResult2 = zacx.getConnectionResult();
            if (!connectionResult2.isSuccess()) {
                final String value = String.valueOf(connectionResult2);
                Log.wtf("SignInCoordinator", new StringBuilder(String.valueOf(value).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(value).toString(), (Throwable)new Exception());
                this.zakj.zag(connectionResult2);
                ((Api.Client)this.zagb).disconnect();
                return;
            }
            this.zakj.zaa(zacx.getAccountAccessor(), this.mScopes);
        }
        else {
            this.zakj.zag(connectionResult);
        }
        ((Api.Client)this.zagb).disconnect();
    }
    
    @WorkerThread
    @Override
    public final void onConnected(@Nullable final Bundle bundle) {
        this.zagb.zaa(this);
    }
    
    @WorkerThread
    @Override
    public final void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        this.zakj.zag(connectionResult);
    }
    
    @WorkerThread
    @Override
    public final void onConnectionSuspended(final int n) {
        ((Api.Client)this.zagb).disconnect();
    }
    
    @WorkerThread
    public final void zaa(final zach zakj) {
        if (this.zagb != null) {
            ((Api.Client)this.zagb).disconnect();
        }
        this.zaet.setClientSessionId(System.identityHashCode(this));
        this.zagb = (com.google.android.gms.signin.zad)this.zaau.buildClient(this.mContext, this.mHandler.getLooper(), this.zaet, this.zaet.getSignInOptions(), this, this);
        this.zakj = zakj;
        if (this.mScopes == null || this.mScopes.isEmpty()) {
            this.mHandler.post((Runnable)new zacf(this));
            return;
        }
        this.zagb.connect();
    }
    
    @BinderThread
    @Override
    public final void zab(final zaj zaj) {
        this.mHandler.post((Runnable)new zacg(this, zaj));
    }
    
    public final com.google.android.gms.signin.zad zabq() {
        return this.zagb;
    }
    
    public final void zabs() {
        if (this.zagb != null) {
            ((Api.Client)this.zagb).disconnect();
        }
    }
}

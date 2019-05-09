package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zaj;
import com.google.android.gms.signin.zaa;
import com.google.android.gms.signin.zad;
import java.util.Set;

public final class zace extends zac implements ConnectionCallbacks, OnConnectionFailedListener {
    private static AbstractClientBuilder<? extends zad, SignInOptions> zaki = zaa.zaph;
    private final Context mContext;
    private final Handler mHandler;
    private Set<Scope> mScopes;
    private final AbstractClientBuilder<? extends zad, SignInOptions> zaau;
    private ClientSettings zaet;
    private zad zagb;
    private zach zakj;

    @WorkerThread
    public zace(Context context, Handler handler, @NonNull ClientSettings clientSettings) {
        this(context, handler, clientSettings, zaki);
    }

    @WorkerThread
    public zace(Context context, Handler handler, @NonNull ClientSettings clientSettings, AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder) {
        this.mContext = context;
        this.mHandler = handler;
        this.zaet = (ClientSettings) Preconditions.checkNotNull(clientSettings, "ClientSettings must not be null");
        this.mScopes = clientSettings.getRequiredScopes();
        this.zaau = abstractClientBuilder;
    }

    @WorkerThread
    public final void zaa(zach zach) {
        if (this.zagb != null) {
            this.zagb.disconnect();
        }
        this.zaet.setClientSessionId(Integer.valueOf(System.identityHashCode(this)));
        this.zagb = (zad) this.zaau.buildClient(this.mContext, this.mHandler.getLooper(), this.zaet, this.zaet.getSignInOptions(), this, this);
        this.zakj = zach;
        if (this.mScopes == null || this.mScopes.isEmpty()) {
            this.mHandler.post(new zacf(this));
        } else {
            this.zagb.connect();
        }
    }

    public final zad zabq() {
        return this.zagb;
    }

    public final void zabs() {
        if (this.zagb != null) {
            this.zagb.disconnect();
        }
    }

    @WorkerThread
    public final void onConnected(@Nullable Bundle bundle) {
        this.zagb.zaa(this);
    }

    @WorkerThread
    public final void onConnectionSuspended(int i) {
        this.zagb.disconnect();
    }

    @WorkerThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zakj.zag(connectionResult);
    }

    @BinderThread
    public final void zab(zaj zaj) {
        this.mHandler.post(new zacg(this, zaj));
    }

    @WorkerThread
    private final void zac(zaj zaj) {
        ConnectionResult connectionResult = zaj.getConnectionResult();
        if (connectionResult.isSuccess()) {
            ResolveAccountResponse zacx = zaj.zacx();
            ConnectionResult connectionResult2 = zacx.getConnectionResult();
            if (connectionResult2.isSuccess()) {
                this.zakj.zaa(zacx.getAccountAccessor(), this.mScopes);
            } else {
                String valueOf = String.valueOf(connectionResult2);
                Log.wtf("SignInCoordinator", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                this.zakj.zag(connectionResult2);
                this.zagb.disconnect();
                return;
            }
        }
        this.zakj.zag(connectionResult);
        this.zagb.disconnect();
    }
}

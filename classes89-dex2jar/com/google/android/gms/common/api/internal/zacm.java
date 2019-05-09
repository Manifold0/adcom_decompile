// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import javax.annotation.concurrent.GuardedBy;
import android.util.Log;
import com.google.android.gms.common.api.Releasable;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.GoogleApiClient;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.api.Result;

public final class zacm<R extends Result> extends TransformedResult<R> implements ResultCallback<R>
{
    private final Object zado;
    private final WeakReference<GoogleApiClient> zadq;
    private ResultTransform<? super R, ? extends Result> zako;
    private zacm<? extends Result> zakp;
    private volatile ResultCallbacks<? super R> zakq;
    private PendingResult<R> zakr;
    private Status zaks;
    private final zaco zakt;
    private boolean zaku;
    
    public zacm(final WeakReference<GoogleApiClient> zadq) {
        this.zako = null;
        this.zakp = null;
        this.zakq = null;
        this.zakr = null;
        this.zado = new Object();
        this.zaks = null;
        this.zaku = false;
        Preconditions.checkNotNull((Object)zadq, (Object)"GoogleApiClient reference must not be null");
        this.zadq = zadq;
        final GoogleApiClient googleApiClient = this.zadq.get();
        Looper looper;
        if (googleApiClient != null) {
            looper = googleApiClient.getLooper();
        }
        else {
            looper = Looper.getMainLooper();
        }
        this.zakt = new zaco(this, looper);
    }
    
    private static void zab(final Result result) {
        if (!(result instanceof Releasable)) {
            return;
        }
        try {
            ((Releasable)result).release();
        }
        catch (RuntimeException ex) {
            final String value = String.valueOf(result);
            Log.w("TransformedResultImpl", new StringBuilder(String.valueOf(value).length() + 18).append("Unable to release ").append(value).toString(), (Throwable)ex);
        }
    }
    
    @GuardedBy("mSyncToken")
    private final void zabu() {
        if (this.zako != null || this.zakq != null) {
            final GoogleApiClient googleApiClient = this.zadq.get();
            if (!this.zaku && this.zako != null && googleApiClient != null) {
                googleApiClient.zaa(this);
                this.zaku = true;
            }
            if (this.zaks != null) {
                this.zae(this.zaks);
                return;
            }
            if (this.zakr != null) {
                this.zakr.setResultCallback((com.google.android.gms.common.api.ResultCallback<? super R>)this);
            }
        }
    }
    
    @GuardedBy("mSyncToken")
    private final boolean zabw() {
        final GoogleApiClient googleApiClient = this.zadq.get();
        return this.zakq != null && googleApiClient != null;
    }
    
    private final void zad(final Status zaks) {
        synchronized (this.zado) {
            this.zae(this.zaks = zaks);
        }
    }
    
    private final void zae(Status onFailure) {
        synchronized (this.zado) {
            if (this.zako != null) {
                onFailure = this.zako.onFailure(onFailure);
                Preconditions.checkNotNull((Object)onFailure, (Object)"onFailure must not return null");
                this.zakp.zad(onFailure);
            }
            else if (this.zabw()) {
                this.zakq.onFailure(onFailure);
            }
        }
    }
    
    @Override
    public final void andFinally(@NonNull final ResultCallbacks<? super R> zakq) {
    Label_0035_Outer:
        while (true) {
            final boolean b = true;
            while (true) {
            Label_0065:
                while (true) {
                    synchronized (this.zado) {
                        if (this.zakq == null) {
                            final boolean b2 = true;
                            Preconditions.checkState(b2, (Object)"Cannot call andFinally() twice.");
                            if (this.zako == null) {
                                final boolean b3 = b;
                                Preconditions.checkState(b3, (Object)"Cannot call then() and andFinally() on the same TransformedResult.");
                                this.zakq = zakq;
                                this.zabu();
                                return;
                            }
                            break Label_0065;
                        }
                    }
                    final boolean b2 = false;
                    continue Label_0035_Outer;
                }
                final boolean b3 = false;
                continue;
            }
        }
    }
    
    public final void onResult(final R r) {
        while (true) {
            synchronized (this.zado) {
                if (r.getStatus().isSuccess()) {
                    if (this.zako != null) {
                        zacc.zabb().submit(new zacn(this, r));
                    }
                    else if (this.zabw()) {
                        this.zakq.onSuccess((Result)r);
                    }
                    return;
                }
            }
            final Result result;
            this.zad(result.getStatus());
            zab(result);
        }
    }
    
    @NonNull
    @Override
    public final <S extends Result> TransformedResult<S> then(@NonNull final ResultTransform<? super R, ? extends S> zako) {
    Label_0035_Outer:
        while (true) {
            final boolean b = true;
            while (true) {
            Label_0083:
                while (true) {
                    synchronized (this.zado) {
                        if (this.zako == null) {
                            final boolean b2 = true;
                            Preconditions.checkState(b2, (Object)"Cannot call then() twice.");
                            if (this.zakq == null) {
                                final boolean b3 = b;
                                Preconditions.checkState(b3, (Object)"Cannot call then() and andFinally() on the same TransformedResult.");
                                this.zako = zako;
                                final zacm<Result> zakp = new zacm<Result>(this.zadq);
                                this.zakp = zakp;
                                this.zabu();
                                return (TransformedResult<S>)zakp;
                            }
                            break Label_0083;
                        }
                    }
                    final boolean b2 = false;
                    continue Label_0035_Outer;
                }
                final boolean b3 = false;
                continue;
            }
        }
    }
    
    public final void zaa(final PendingResult<?> zakr) {
        synchronized (this.zado) {
            this.zakr = (PendingResult<R>)zakr;
            this.zabu();
        }
    }
    
    final void zabv() {
        this.zakq = null;
    }
}

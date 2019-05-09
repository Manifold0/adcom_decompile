package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import javax.annotation.concurrent.GuardedBy;

public final class zacm<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private final Object zado = new Object();
    private final WeakReference<GoogleApiClient> zadq;
    private ResultTransform<? super R, ? extends Result> zako = null;
    private zacm<? extends Result> zakp = null;
    private volatile ResultCallbacks<? super R> zakq = null;
    private PendingResult<R> zakr = null;
    private Status zaks = null;
    private final zaco zakt;
    private boolean zaku = false;

    public zacm(WeakReference<GoogleApiClient> weakReference) {
        Preconditions.checkNotNull(weakReference, "GoogleApiClient reference must not be null");
        this.zadq = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) this.zadq.get();
        this.zakt = new zaco(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult zacm;
        boolean z = true;
        synchronized (this.zado) {
            Preconditions.checkState(this.zako == null, "Cannot call then() twice.");
            if (this.zakq != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zako = resultTransform;
            zacm = new zacm(this.zadq);
            this.zakp = zacm;
            zabu();
        }
        return zacm;
    }

    public final void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        boolean z = true;
        synchronized (this.zado) {
            boolean z2;
            if (this.zakq == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            Preconditions.checkState(z2, "Cannot call andFinally() twice.");
            if (this.zako != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zakq = resultCallbacks;
            zabu();
        }
    }

    public final void onResult(R r) {
        synchronized (this.zado) {
            if (!r.getStatus().isSuccess()) {
                zad(r.getStatus());
                zab(r);
            } else if (this.zako != null) {
                zacc.zabb().submit(new zacn(this, r));
            } else if (zabw()) {
                this.zakq.onSuccess(r);
            }
        }
    }

    public final void zaa(PendingResult<?> pendingResult) {
        synchronized (this.zado) {
            this.zakr = pendingResult;
            zabu();
        }
    }

    @GuardedBy("mSyncToken")
    private final void zabu() {
        if (this.zako != null || this.zakq != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zadq.get();
            if (!(this.zaku || this.zako == null || googleApiClient == null)) {
                googleApiClient.zaa(this);
                this.zaku = true;
            }
            if (this.zaks != null) {
                zae(this.zaks);
            } else if (this.zakr != null) {
                this.zakr.setResultCallback(this);
            }
        }
    }

    private final void zad(Status status) {
        synchronized (this.zado) {
            this.zaks = status;
            zae(this.zaks);
        }
    }

    private final void zae(Status status) {
        synchronized (this.zado) {
            if (this.zako != null) {
                Status onFailure = this.zako.onFailure(status);
                Preconditions.checkNotNull(onFailure, "onFailure must not return null");
                this.zakp.zad(onFailure);
            } else if (zabw()) {
                this.zakq.onFailure(status);
            }
        }
    }

    final void zabv() {
        this.zakq = null;
    }

    @GuardedBy("mSyncToken")
    private final boolean zabw() {
        return (this.zakq == null || ((GoogleApiClient) this.zadq.get()) == null) ? false : true;
    }

    private static void zab(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                String valueOf = String.valueOf(result);
                Log.w("TransformedResultImpl", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }
}

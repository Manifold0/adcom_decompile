package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class zze extends AsyncTaskLoader<Void> implements SignInConnectionListener {
    private Semaphore zzbg = new Semaphore(0);
    private Set<GoogleApiClient> zzbh;

    public zze(Context context, Set<GoogleApiClient> set) {
        super(context);
        this.zzbh = set;
    }

    private final Void zzf() {
        int i = 0;
        for (GoogleApiClient maybeSignIn : this.zzbh) {
            int i2;
            if (maybeSignIn.maybeSignIn(this)) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        try {
            this.zzbg.tryAcquire(i, 5, TimeUnit.SECONDS);
        } catch (Throwable e) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
            Thread.currentThread().interrupt();
        }
        return null;
    }

    protected final void onStartLoading() {
        this.zzbg.drainPermits();
        forceLoad();
    }

    public final void onComplete() {
        this.zzbg.release();
    }

    public final /* synthetic */ Object loadInBackground() {
        return zzf();
    }
}

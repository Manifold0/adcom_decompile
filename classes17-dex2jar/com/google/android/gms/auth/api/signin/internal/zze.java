// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import java.util.Iterator;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Set;
import java.util.concurrent.Semaphore;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import android.support.v4.content.AsyncTaskLoader;

public final class zze extends AsyncTaskLoader<Void> implements SignInConnectionListener
{
    private Semaphore zzbg;
    private Set<GoogleApiClient> zzbh;
    
    public zze(final Context context, final Set<GoogleApiClient> zzbh) {
        super(context);
        this.zzbg = new Semaphore(0);
        this.zzbh = zzbh;
    }
    
    private final Void zzf() {
        final Iterator<GoogleApiClient> iterator = this.zzbh.iterator();
        int n = 0;
    Label_0041_Outer:
        while (true) {
            if (iterator.hasNext()) {
                if (!iterator.next().maybeSignIn((SignInConnectionListener)this)) {
                    continue Label_0041_Outer;
                }
                ++n;
            }
            else {
                try {
                    this.zzbg.tryAcquire(n, 5L, TimeUnit.SECONDS);
                    return null;
                }
                catch (InterruptedException ex) {
                    Log.i("GACSignInLoader", "Unexpected InterruptedException", (Throwable)ex);
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            while (true) {
                continue Label_0041_Outer;
                continue;
            }
        }
    }
    
    public final void onComplete() {
        this.zzbg.release();
    }
    
    protected final void onStartLoading() {
        this.zzbg.drainPermits();
        this.forceLoad();
    }
}

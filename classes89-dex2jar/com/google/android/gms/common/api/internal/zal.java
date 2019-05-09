// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Parcelable;
import android.os.Bundle;
import android.content.DialogInterface;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zap;
import android.os.Looper;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicReference;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.DialogInterface$OnCancelListener;

public abstract class zal extends LifecycleCallback implements DialogInterface$OnCancelListener
{
    protected volatile boolean mStarted;
    protected final GoogleApiAvailability zacd;
    protected final AtomicReference<zam> zadf;
    private final Handler zadg;
    
    protected zal(final LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.getInstance());
    }
    
    @VisibleForTesting
    private zal(final LifecycleFragment lifecycleFragment, final GoogleApiAvailability zacd) {
        super(lifecycleFragment);
        this.zadf = new AtomicReference<zam>(null);
        this.zadg = new zap(Looper.getMainLooper());
        this.zacd = zacd;
    }
    
    private static int zaa(@Nullable final zam zam) {
        if (zam == null) {
            return -1;
        }
        return zam.zar();
    }
    
    public void onActivityResult(int intExtra, int n, final Intent intent) {
        final int n2 = 13;
        final zam zam = this.zadf.get();
        zam zam2 = null;
        Label_0045: {
            switch (intExtra) {
                case 2: {
                    final int googlePlayServicesAvailable = this.zacd.isGooglePlayServicesAvailable((Context)this.getActivity());
                    if (googlePlayServicesAvailable == 0) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    if (zam == null) {
                        return;
                    }
                    zam2 = zam;
                    intExtra = n;
                    if (zam.getConnectionResult().getErrorCode() != 18) {
                        break Label_0045;
                    }
                    zam2 = zam;
                    intExtra = n;
                    if (googlePlayServicesAvailable == 18) {
                        return;
                    }
                    break Label_0045;
                }
                case 1: {
                    if (n == -1) {
                        intExtra = 1;
                        zam2 = zam;
                        break Label_0045;
                    }
                    if (n == 0) {
                        intExtra = n2;
                        if (intent != null) {
                            intExtra = intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
                        }
                        zam2 = new zam(new ConnectionResult(intExtra, (PendingIntent)null), zaa(zam));
                        this.zadf.set(zam2);
                        intExtra = 0;
                        break Label_0045;
                    }
                    break;
                }
            }
            intExtra = 0;
            zam2 = zam;
        }
        if (intExtra != 0) {
            this.zaq();
        }
        else if (zam2 != null) {
            this.zaa(zam2.getConnectionResult(), zam2.zar());
        }
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.zaa(new ConnectionResult(13, (PendingIntent)null), zaa(this.zadf.get()));
        this.zaq();
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            final AtomicReference<zam> zadf = this.zadf;
            zam zam;
            if (bundle.getBoolean("resolving_error", false)) {
                zam = new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent)bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1));
            }
            else {
                zam = null;
            }
            zadf.set(zam);
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        final zam zam = this.zadf.get();
        if (zam != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zam.zar());
            bundle.putInt("failed_status", zam.getConnectionResult().getErrorCode());
            bundle.putParcelable("failed_resolution", (Parcelable)zam.getConnectionResult().getResolution());
        }
    }
    
    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }
    
    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }
    
    protected abstract void zaa(final ConnectionResult p0, final int p1);
    
    public final void zab(final ConnectionResult connectionResult, final int n) {
        final zam zam = new zam(connectionResult, n);
        if (this.zadf.compareAndSet(null, zam)) {
            this.zadg.post((Runnable)new zan(this, zam));
        }
    }
    
    protected abstract void zao();
    
    protected final void zaq() {
        this.zadf.set(null);
        this.zao();
    }
}

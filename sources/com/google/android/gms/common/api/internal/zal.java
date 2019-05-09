package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zal extends LifecycleCallback implements OnCancelListener {
    protected volatile boolean mStarted;
    protected final GoogleApiAvailability zacd;
    protected final AtomicReference<zam> zadf;
    private final Handler zadg;

    protected zal(LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.getInstance());
    }

    protected abstract void zaa(ConnectionResult connectionResult, int i);

    protected abstract void zao();

    @VisibleForTesting
    private zal(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.zadf = new AtomicReference(null);
        this.zadg = new zap(Looper.getMainLooper());
        this.zacd = googleApiAvailability;
    }

    public void onCancel(DialogInterface dialogInterface) {
        zaa(new ConnectionResult(13, null), zaa((zam) this.zadf.get()));
        zaq();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            Object zam;
            AtomicReference atomicReference = this.zadf;
            if (bundle.getBoolean("resolving_error", false)) {
                zam = new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1));
            } else {
                zam = null;
            }
            atomicReference.set(zam);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zam zam = (zam) this.zadf.get();
        if (zam != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zam.zar());
            bundle.putInt("failed_status", zam.getConnectionResult().getErrorCode());
            bundle.putParcelable("failed_resolution", zam.getConnectionResult().getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r7, int r8, android.content.Intent r9) {
        /*
        r6 = this;
        r5 = 18;
        r1 = 13;
        r2 = 1;
        r3 = 0;
        r0 = r6.zadf;
        r0 = r0.get();
        r0 = (com.google.android.gms.common.api.internal.zam) r0;
        switch(r7) {
            case 1: goto L_0x0034;
            case 2: goto L_0x0018;
            default: goto L_0x0011;
        };
    L_0x0011:
        r1 = r3;
    L_0x0012:
        if (r1 == 0) goto L_0x005a;
    L_0x0014:
        r6.zaq();
    L_0x0017:
        return;
    L_0x0018:
        r1 = r6.zacd;
        r4 = r6.getActivity();
        r4 = r1.isGooglePlayServicesAvailable(r4);
        if (r4 != 0) goto L_0x0068;
    L_0x0024:
        r1 = r2;
    L_0x0025:
        if (r0 == 0) goto L_0x0017;
    L_0x0027:
        r2 = r0.getConnectionResult();
        r2 = r2.getErrorCode();
        if (r2 != r5) goto L_0x0012;
    L_0x0031:
        if (r4 != r5) goto L_0x0012;
    L_0x0033:
        goto L_0x0017;
    L_0x0034:
        r4 = -1;
        if (r8 != r4) goto L_0x0039;
    L_0x0037:
        r1 = r2;
        goto L_0x0012;
    L_0x0039:
        if (r8 != 0) goto L_0x0011;
    L_0x003b:
        if (r9 == 0) goto L_0x0043;
    L_0x003d:
        r2 = "<<ResolutionFailureErrorDetail>>";
        r1 = r9.getIntExtra(r2, r1);
    L_0x0043:
        r2 = new com.google.android.gms.common.api.internal.zam;
        r4 = new com.google.android.gms.common.ConnectionResult;
        r5 = 0;
        r4.<init>(r1, r5);
        r0 = zaa(r0);
        r2.<init>(r4, r0);
        r0 = r6.zadf;
        r0.set(r2);
        r0 = r2;
        r1 = r3;
        goto L_0x0012;
    L_0x005a:
        if (r0 == 0) goto L_0x0017;
    L_0x005c:
        r1 = r0.getConnectionResult();
        r0 = r0.zar();
        r6.zaa(r1, r0);
        goto L_0x0017;
    L_0x0068:
        r1 = r3;
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zal.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    protected final void zaq() {
        this.zadf.set(null);
        zao();
    }

    public final void zab(ConnectionResult connectionResult, int i) {
        zam zam = new zam(connectionResult, i);
        if (this.zadf.compareAndSet(null, zam)) {
            this.zadg.post(new zan(this, zam));
        }
    }

    private static int zaa(@Nullable zam zam) {
        if (zam == null) {
            return -1;
        }
        return zam.zar();
    }
}

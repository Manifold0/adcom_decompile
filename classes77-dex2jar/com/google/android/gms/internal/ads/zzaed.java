// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.DeadObjectException;
import android.os.Binder;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import android.content.Context;
import com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks;

@zzadh
public final class zzaed extends zzadz implements BaseGmsClient$BaseConnectionCallbacks, BaseGmsClient$BaseOnConnectionFailedListener
{
    private Context mContext;
    private final Object mLock;
    private zzaol<zzaef> zzccp;
    private final zzadx zzccq;
    @VisibleForTesting
    private zzaee zzcct;
    private zzang zzyf;
    
    public zzaed(final Context mContext, final zzang zzyf, final zzaol<zzaef> zzccp, final zzadx zzccq) {
        super(zzccp, zzccq);
        this.mLock = new Object();
        this.mContext = mContext;
        this.zzyf = zzyf;
        this.zzccp = zzccp;
        this.zzccq = zzccq;
        Looper looper;
        if (zzkb.zzik().zzd(zznk.zzavz)) {
            looper = zzbv.zzez().zzsa();
        }
        else {
            looper = mContext.getMainLooper();
        }
        (this.zzcct = new zzaee(mContext, looper, (BaseGmsClient$BaseConnectionCallbacks)this, (BaseGmsClient$BaseOnConnectionFailedListener)this)).checkAvailabilityAndConnect();
    }
    
    public final void onConnected(final Bundle bundle) {
        this.zznt();
    }
    
    public final void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        zzakb.zzck("Cannot connect to remote service, fallback to local instance.");
        new zzaec(this.mContext, this.zzccp, this.zzccq).zznt();
        final Bundle bundle = new Bundle();
        bundle.putString("action", "gms_connection_failed_fallback_to_local");
        zzbv.zzek().zzb(this.mContext, this.zzyf.zzcw, "gmob-apps", bundle, true);
    }
    
    public final void onConnectionSuspended(final int n) {
        zzakb.zzck("Disconnected from remote ad request service.");
    }
    
    @Override
    public final void zznz() {
        synchronized (this.mLock) {
            if (this.zzcct.isConnected() || this.zzcct.isConnecting()) {
                this.zzcct.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }
    
    @Override
    public final zzaen zzoa() {
        final Object mLock = this.mLock;
        // monitorenter(mLock)
        while (true) {
            try {
                try {
                    return this.zzcct.zzob();
                    // monitorexit(mLock)
                    return null;
                }
                finally {
                }
                // monitorexit(mLock)
            }
            catch (DeadObjectException ex) {
                continue;
            }
            catch (IllegalStateException ex2) {
                continue;
            }
            break;
        }
    }
}

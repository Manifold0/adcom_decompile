// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.ObjectWrapper;
import android.os.Bundle;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.ads.internal.zzw;
import javax.annotation.concurrent.GuardedBy;
import android.content.Context;

@zzadh
public final class zzagq extends zzaha
{
    private final Context mContext;
    private final Object mLock;
    @GuardedBy("mLock")
    private final zzagr zzcld;
    private final zzang zzyf;
    
    public zzagq(final Context context, final zzw zzw, final zzxn zzxn, final zzang zzang) {
        this(context, zzang, new zzagr(context, zzw, zzjn.zzhx(), zzxn, zzang));
    }
    
    @VisibleForTesting
    private zzagq(final Context mContext, final zzang zzyf, final zzagr zzcld) {
        this.mLock = new Object();
        this.mContext = mContext;
        this.zzyf = zzyf;
        this.zzcld = zzcld;
    }
    
    public final void destroy() {
        this.zzf(null);
    }
    
    public final String getMediationAdapterClassName() {
        synchronized (this.mLock) {
            return this.zzcld.getMediationAdapterClassName();
        }
    }
    
    public final boolean isLoaded() {
        synchronized (this.mLock) {
            return this.zzcld.isLoaded();
        }
    }
    
    public final void pause() {
        this.zzd(null);
    }
    
    public final void resume() {
        this.zze(null);
    }
    
    public final void setImmersiveMode(final boolean immersiveMode) {
        synchronized (this.mLock) {
            this.zzcld.setImmersiveMode(immersiveMode);
        }
    }
    
    public final void setUserId(final String userId) {
        synchronized (this.mLock) {
            this.zzcld.setUserId(userId);
        }
    }
    
    public final void show() {
        synchronized (this.mLock) {
            this.zzcld.zzoy();
        }
    }
    
    public final void zza(final zzagx zzagx) {
        synchronized (this.mLock) {
            this.zzcld.zza(zzagx);
        }
    }
    
    public final void zza(final zzahe zzahe) {
        synchronized (this.mLock) {
            this.zzcld.zza(zzahe);
        }
    }
    
    public final void zza(final zzahk zzahk) {
        synchronized (this.mLock) {
            this.zzcld.zza(zzahk);
        }
    }
    
    public final void zza(final zzkx zzkx) {
        if (zzkb.zzik().zzd(zznk.zzayf)) {
            synchronized (this.mLock) {
                this.zzcld.zza(zzkx);
            }
        }
    }
    
    public final Bundle zzba() {
        if (zzkb.zzik().zzd(zznk.zzayf)) {
            synchronized (this.mLock) {
                return this.zzcld.zzba();
            }
        }
        return new Bundle();
    }
    
    public final void zzd(final IObjectWrapper objectWrapper) {
        synchronized (this.mLock) {
            this.zzcld.pause();
        }
    }
    
    public final void zze(final IObjectWrapper objectWrapper) {
        final Object mLock = this.mLock;
        // monitorenter(mLock)
        Label_0035: {
            if (objectWrapper != null) {
                break Label_0035;
            }
            final Context context = null;
        Label_0025_Outer:
            while (true) {
                while (true) {
                    if (context == null) {
                        break Label_0025;
                    }
                    try {
                        this.zzcld.onContextChanged(context);
                        this.zzcld.resume();
                        return;
                        final Context context2 = (Context)ObjectWrapper.unwrap(objectWrapper);
                        continue Label_0025_Outer;
                    }
                    catch (Exception ex) {
                        zzakb.zzc("Unable to extract updated context.", (Throwable)ex);
                        continue;
                    }
                    finally {
                    }
                    // monitorexit(mLock)
                    break;
                }
                break;
            }
        }
    }
    
    public final void zzf(final IObjectWrapper objectWrapper) {
        synchronized (this.mLock) {
            this.zzcld.destroy();
        }
    }
}

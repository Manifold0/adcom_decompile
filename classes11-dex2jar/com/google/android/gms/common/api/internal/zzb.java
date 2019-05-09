// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzb implements Runnable
{
    private final /* synthetic */ LifecycleCallback zzbi;
    private final /* synthetic */ String zzbj;
    private final /* synthetic */ zza zzbk;
    
    zzb(final zza zzbk, final LifecycleCallback zzbi, final String zzbj) {
        this.zzbk = zzbk;
        this.zzbi = zzbi;
        this.zzbj = zzbj;
    }
    
    @Override
    public final void run() {
        if (this.zzbk.zzbg > 0) {
            final LifecycleCallback zzbi = this.zzbi;
            Bundle bundle;
            if (this.zzbk.zzbh != null) {
                bundle = this.zzbk.zzbh.getBundle(this.zzbj);
            }
            else {
                bundle = null;
            }
            zzbi.onCreate(bundle);
        }
        if (this.zzbk.zzbg >= 2) {
            this.zzbi.onStart();
        }
        if (this.zzbk.zzbg >= 3) {
            this.zzbi.onResume();
        }
        if (this.zzbk.zzbg >= 4) {
            this.zzbi.onStop();
        }
        if (this.zzbk.zzbg >= 5) {
            this.zzbi.onDestroy();
        }
    }
}

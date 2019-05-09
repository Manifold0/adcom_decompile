// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Bundle;

final class zzd implements Runnable
{
    private final /* synthetic */ LifecycleCallback zzbi;
    private final /* synthetic */ String zzbj;
    private final /* synthetic */ zzc zzbl;
    
    zzd(final zzc zzbl, final LifecycleCallback zzbi, final String zzbj) {
        this.zzbl = zzbl;
        this.zzbi = zzbi;
        this.zzbj = zzbj;
    }
    
    @Override
    public final void run() {
        if (this.zzbl.zzbg > 0) {
            final LifecycleCallback zzbi = this.zzbi;
            Bundle bundle;
            if (this.zzbl.zzbh != null) {
                bundle = this.zzbl.zzbh.getBundle(this.zzbj);
            }
            else {
                bundle = null;
            }
            zzbi.onCreate(bundle);
        }
        if (this.zzbl.zzbg >= 2) {
            this.zzbi.onStart();
        }
        if (this.zzbl.zzbg >= 3) {
            this.zzbi.onResume();
        }
        if (this.zzbl.zzbg >= 4) {
            this.zzbi.onStop();
        }
        if (this.zzbl.zzbg >= 5) {
            this.zzbi.onDestroy();
        }
    }
}

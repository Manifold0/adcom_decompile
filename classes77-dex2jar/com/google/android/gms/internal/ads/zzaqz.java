// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.View;

final class zzaqz implements Runnable
{
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ int zzdch;
    private final /* synthetic */ zzaqx zzdci;
    
    zzaqz(final zzaqx zzdci, final View val$view, final zzait zzdcg, final int zzdch) {
        this.zzdci = zzdci;
        this.val$view = val$view;
        this.zzdcg = zzdcg;
        this.zzdch = zzdch;
    }
    
    @Override
    public final void run() {
        this.zzdci.zza(this.val$view, this.zzdcg, this.zzdch - 1);
    }
}

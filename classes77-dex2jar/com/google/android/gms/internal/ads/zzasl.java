// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.View;

final class zzasl implements Runnable
{
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ int zzdch;
    private final /* synthetic */ zzasj zzdes;
    
    zzasl(final zzasj zzdes, final View val$view, final zzait zzdcg, final int zzdch) {
        this.zzdes = zzdes;
        this.val$view = val$view;
        this.zzdcg = zzdcg;
        this.zzdch = zzdch;
    }
    
    @Override
    public final void run() {
        this.zzdes.zza(this.val$view, this.zzdcg, this.zzdch - 1);
    }
}

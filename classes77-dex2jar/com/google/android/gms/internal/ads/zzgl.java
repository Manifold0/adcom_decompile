// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.View;

final class zzgl implements Runnable
{
    private final /* synthetic */ View zzaij;
    private final /* synthetic */ zzgk zzaik;
    
    zzgl(final zzgk zzaik, final View zzaij) {
        this.zzaik = zzaik;
        this.zzaij = zzaij;
    }
    
    @Override
    public final void run() {
        this.zzaik.zzk(this.zzaij);
    }
}

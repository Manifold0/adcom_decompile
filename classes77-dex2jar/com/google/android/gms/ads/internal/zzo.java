// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.view.View;
import android.view.View$OnClickListener;

final class zzo implements View$OnClickListener
{
    private final /* synthetic */ zzl zzwp;
    private final /* synthetic */ zzx zzwq;
    
    zzo(final zzl zzwp, final zzx zzwq) {
        this.zzwp = zzwp;
        this.zzwq = zzwq;
    }
    
    public final void onClick(final View view) {
        this.zzwq.recordClick();
        if (this.zzwp.zzwn != null) {
            this.zzwp.zzwn.zzpi();
        }
    }
}

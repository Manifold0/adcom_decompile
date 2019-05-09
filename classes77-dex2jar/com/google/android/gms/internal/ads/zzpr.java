// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;

final class zzpr implements zzox
{
    private final /* synthetic */ View zzbkc;
    private final /* synthetic */ zzpp zzbkj;
    
    zzpr(final zzpp zzbkj, final View zzbkc) {
        this.zzbkj = zzbkj;
        this.zzbkc = zzbkc;
    }
    
    @Override
    public final void zzc(final MotionEvent motionEvent) {
        this.zzbkj.onTouch(null, motionEvent);
    }
    
    @Override
    public final void zzki() {
        if (this.zzbkj.zza(zzpp.zzbjs)) {
            this.zzbkj.onClick(this.zzbkc);
        }
    }
}

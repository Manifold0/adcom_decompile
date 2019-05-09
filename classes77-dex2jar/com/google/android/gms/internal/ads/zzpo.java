// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;

final class zzpo implements zzox
{
    private final /* synthetic */ View zzbkc;
    private final /* synthetic */ zzpn zzbkd;
    
    zzpo(final zzpn zzbkd, final View zzbkc) {
        this.zzbkd = zzbkd;
        this.zzbkc = zzbkc;
    }
    
    @Override
    public final void zzc(final MotionEvent motionEvent) {
        this.zzbkd.onTouch(null, motionEvent);
    }
    
    @Override
    public final void zzki() {
        this.zzbkd.onClick(this.zzbkc);
    }
}

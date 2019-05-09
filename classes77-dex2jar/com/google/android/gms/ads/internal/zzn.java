// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

final class zzn implements View$OnTouchListener
{
    private final /* synthetic */ zzl zzwp;
    private final /* synthetic */ zzx zzwq;
    
    zzn(final zzl zzwp, final zzx zzwq) {
        this.zzwp = zzwp;
        this.zzwq = zzwq;
    }
    
    public final boolean onTouch(final View view, final MotionEvent motionEvent) {
        this.zzwq.recordClick();
        if (this.zzwp.zzwn != null) {
            this.zzwp.zzwn.zzpi();
        }
        return false;
    }
}

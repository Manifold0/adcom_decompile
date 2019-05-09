// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

final class zzbr implements View$OnTouchListener
{
    private final /* synthetic */ zzbp zzaba;
    
    zzbr(final zzbp zzaba) {
        this.zzaba = zzaba;
    }
    
    public final boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (this.zzaba.zzaay != null) {
            this.zzaba.zzaay.zza(motionEvent);
        }
        return false;
    }
}

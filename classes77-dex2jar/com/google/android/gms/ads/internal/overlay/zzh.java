// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.MotionEvent;
import android.content.Context;
import com.google.android.gms.internal.ads.zzald;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import android.widget.RelativeLayout;

@zzadh
@VisibleForTesting
final class zzh extends RelativeLayout
{
    @VisibleForTesting
    private zzald zzaed;
    @VisibleForTesting
    boolean zzbyh;
    
    public zzh(final Context context, final String s, final String s2) {
        super(context);
        (this.zzaed = new zzald(context, s)).zzda(s2);
    }
    
    public final boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        if (!this.zzbyh) {
            this.zzaed.zze(motionEvent);
        }
        return false;
    }
}

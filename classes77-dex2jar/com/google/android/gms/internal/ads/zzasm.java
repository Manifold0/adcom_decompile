// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.View$OnAttachStateChangeListener;

final class zzasm implements View$OnAttachStateChangeListener
{
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ zzasj zzdes;
    
    zzasm(final zzasj zzdes, final zzait zzdcg) {
        this.zzdes = zzdes;
        this.zzdcg = zzdcg;
    }
    
    public final void onViewAttachedToWindow(final View view) {
        this.zzdes.zza(view, this.zzdcg, 10);
    }
    
    public final void onViewDetachedFromWindow(final View view) {
    }
}

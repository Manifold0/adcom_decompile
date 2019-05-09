// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.View$OnAttachStateChangeListener;

final class zzara implements View$OnAttachStateChangeListener
{
    private final /* synthetic */ zzait zzdcg;
    private final /* synthetic */ zzaqx zzdci;
    
    zzara(final zzaqx zzdci, final zzait zzdcg) {
        this.zzdci = zzdci;
        this.zzdcg = zzdcg;
    }
    
    public final void onViewAttachedToWindow(final View view) {
        this.zzdci.zza(view, this.zzdcg, 10);
    }
    
    public final void onViewDetachedFromWindow(final View view) {
    }
}

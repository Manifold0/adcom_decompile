// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnFailureListener;

final class zzl implements OnFailureListener
{
    private final /* synthetic */ RegisterListenerMethod zzap;
    private final /* synthetic */ zzk zzaq;
    
    zzl(final zzk zzaq, final RegisterListenerMethod zzap) {
        this.zzaq = zzaq;
        this.zzap = zzap;
    }
    
    public final void onFailure(final Exception ex) {
        synchronized (this.zzaq) {
            this.zzaq.zzan.remove(this.zzap.getListenerKey());
        }
    }
}

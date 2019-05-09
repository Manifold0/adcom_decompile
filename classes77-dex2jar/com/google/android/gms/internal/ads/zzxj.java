// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class zzxj implements Runnable
{
    private final /* synthetic */ zzxh zzbuk;
    private final /* synthetic */ zzanz zzbul;
    
    zzxj(final zzxh zzbuk, final zzanz zzbul) {
        this.zzbuk = zzbuk;
        this.zzbul = zzbul;
    }
    
    @Override
    public final void run() {
        for (final zzanz zzanz : this.zzbuk.zzbug.keySet()) {
            if (zzanz != this.zzbul) {
                ((zzxb)this.zzbuk.zzbug.get(zzanz)).cancel();
            }
        }
    }
}

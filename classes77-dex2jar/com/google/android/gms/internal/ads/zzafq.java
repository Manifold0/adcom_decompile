// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final class zzafq implements Runnable
{
    private final /* synthetic */ Future zzchn;
    
    zzafq(final zzafn zzafn, final Future zzchn) {
        this.zzchn = zzchn;
    }
    
    @Override
    public final void run() {
        if (!this.zzchn.isDone()) {
            this.zzchn.cancel(true);
        }
    }
}

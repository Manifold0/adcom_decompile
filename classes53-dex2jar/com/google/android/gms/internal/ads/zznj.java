// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zznj implements Callable<Object>
{
    private final /* synthetic */ zzna zzaty;
    private final /* synthetic */ zzni zzatz;
    
    zznj(final zzni zzatz, final zzna zzaty) {
        this.zzatz = zzatz;
        this.zzaty = zzaty;
    }
    
    @Override
    public final Object call() {
        return this.zzaty.zza(this.zzatz.zzatw);
    }
}

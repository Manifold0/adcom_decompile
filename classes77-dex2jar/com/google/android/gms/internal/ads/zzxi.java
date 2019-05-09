// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzxi implements Callable<zzxe>
{
    private final /* synthetic */ zzxb zzbuj;
    private final /* synthetic */ zzxh zzbuk;
    
    zzxi(final zzxh zzbuk, final zzxb zzbuj) {
        this.zzbuk = zzbuk;
        this.zzbuj = zzbuj;
    }
    
    private final zzxe zzmn() throws Exception {
        synchronized (this.zzbuk.mLock) {
            if (this.zzbuk.zzbuf) {
                return null;
            }
            // monitorexit(zzxh.zza(this.zzbuk))
            return this.zzbuj.zza(this.zzbuk.mStartTime, this.zzbuk.zzbud);
        }
    }
}

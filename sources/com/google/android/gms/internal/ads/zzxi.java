package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzxi implements Callable<zzxe> {
    private final /* synthetic */ zzxb zzbuj;
    private final /* synthetic */ zzxh zzbuk;

    zzxi(zzxh zzxh, zzxb zzxb) {
        this.zzbuk = zzxh;
        this.zzbuj = zzxb;
    }

    private final zzxe zzmn() throws Exception {
        synchronized (this.zzbuk.mLock) {
            if (this.zzbuk.zzbuf) {
                return null;
            }
            return this.zzbuj.zza(this.zzbuk.mStartTime, this.zzbuk.zzbud);
        }
    }

    public final /* synthetic */ Object call() throws Exception {
        return zzmn();
    }
}

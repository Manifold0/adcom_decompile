package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.ObjectWrapper;

final class zzahp implements Runnable {
    private final /* synthetic */ zzxq zzclu;
    private final /* synthetic */ zzahn zzclv;
    private final /* synthetic */ zzahv zzclw;
    private final /* synthetic */ zzjj zzyh;

    zzahp(zzahn zzahn, zzxq zzxq, zzjj zzjj, zzahv zzahv) {
        this.zzclv = zzahn;
        this.zzclu = zzxq;
        this.zzyh = zzjj;
        this.zzclw = zzahv;
    }

    public final void run() {
        try {
            this.zzclu.zza(ObjectWrapper.wrap(this.zzclv.mContext), this.zzyh, null, this.zzclw, this.zzclv.zzcln);
        } catch (Throwable e) {
            Throwable th = e;
            String str = "Fail to initialize adapter ";
            String valueOf = String.valueOf(this.zzclv.zzbth);
            zzakb.zzc(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), th);
            this.zzclv.zza(this.zzclv.zzbth, 0);
        }
    }
}

package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzjv extends zza<zzkn> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzarr;
    private final /* synthetic */ zzxn zzars;
    private final /* synthetic */ zzjr zzart;

    zzjv(zzjr zzjr, Context context, String str, zzxn zzxn) {
        this.zzart = zzjr;
        this.val$context = context;
        this.zzarr = str;
        this.zzars = zzxn;
        super(zzjr);
    }

    public final /* synthetic */ Object zza(zzld zzld) throws RemoteException {
        return zzld.createAdLoaderBuilder(ObjectWrapper.wrap(this.val$context), this.zzarr, this.zzars, 12451000);
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzkn zza = this.zzart.zzark.zza(this.val$context, this.zzarr, this.zzars);
        if (zza != null) {
            return zza;
        }
        zzjr.zza(this.val$context, "native_ad");
        return new zzmf();
    }
}

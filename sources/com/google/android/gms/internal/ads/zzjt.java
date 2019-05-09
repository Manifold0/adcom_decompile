package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.applovin.sdk.AppLovinEventTypes;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzjt extends zza<zzks> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzjn zzarq;
    private final /* synthetic */ String zzarr;
    private final /* synthetic */ zzjr zzart;

    zzjt(zzjr zzjr, Context context, zzjn zzjn, String str) {
        this.zzart = zzjr;
        this.val$context = context;
        this.zzarq = zzjn;
        this.zzarr = str;
        super(zzjr);
    }

    public final /* synthetic */ Object zza(zzld zzld) throws RemoteException {
        return zzld.createSearchAdManager(ObjectWrapper.wrap(this.val$context), this.zzarq, this.zzarr, 12451000);
    }

    public final /* synthetic */ Object zzib() throws RemoteException {
        zzks zza = this.zzart.zzarj.zza(this.val$context, this.zzarq, this.zzarr, null, 3);
        if (zza != null) {
            return zza;
        }
        zzjr.zza(this.val$context, AppLovinEventTypes.USER_EXECUTED_SEARCH);
        return new zzmj();
    }
}

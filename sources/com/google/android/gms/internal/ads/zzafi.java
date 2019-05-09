package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import com.unity.purchasing.googleplay.Consts;
import java.util.Map;

public final class zzafi implements zzv<Object> {
    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get(Consts.INAPP_REQUEST_ID);
        String str2 = "Invalid request: ";
        String valueOf = String.valueOf((String) map.get("errors"));
        zzakb.zzdk(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        zzafa.zzcgg.zzat(str);
    }
}

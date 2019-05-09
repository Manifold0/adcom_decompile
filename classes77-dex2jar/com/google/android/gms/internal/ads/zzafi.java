// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;

public final class zzafi implements zzv<Object>
{
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        final String s = map.get("request_id");
        final String value = String.valueOf(map.get("errors"));
        String concat;
        if (value.length() != 0) {
            concat = "Invalid request: ".concat(value);
        }
        else {
            concat = new String("Invalid request: ");
        }
        zzakb.zzdk(concat);
        zzafa.zzcgg.zzat(s);
    }
}

// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzakb;
import java.util.Map;

final class zzp implements zzv<Object>
{
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        final String value = String.valueOf(map.get("string"));
        String concat;
        if (value.length() != 0) {
            concat = "Received log message: ".concat(value);
        }
        else {
            concat = new String("Received log message: ");
        }
        zzakb.zzdj(concat);
    }
}

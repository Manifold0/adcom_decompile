// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;

@zzadh
public final class zzaql implements zzv<zzapw>
{
    private static Integer zze(final Map<String, String> map, final String s) {
        if (!map.containsKey(s)) {
            return null;
        }
        try {
            return Integer.parseInt(map.get(s));
        }
        catch (NumberFormatException ex) {
            final String s2 = map.get(s);
            zzakb.zzdk(new StringBuilder(String.valueOf(s).length() + 39 + String.valueOf(s2).length()).append("Precache invalid numeric parameter '").append(s).append("': ").append(s2).toString());
            return null;
        }
    }
}

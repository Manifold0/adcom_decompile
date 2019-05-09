// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzakb;
import java.util.Map;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zzy implements zzv<Object>
{
    private final zzz zzbmu;
    
    public zzy(final zzz zzbmu) {
        this.zzbmu = zzbmu;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        while (true) {
            final boolean equals = "1".equals(map.get("transparentBackground"));
            final boolean equals2 = "1".equals(map.get("blur"));
            while (true) {
                try {
                    if (map.get("blurRadius") != null) {
                        final float float1 = Float.parseFloat(map.get("blurRadius"));
                        this.zzbmu.zzd(equals);
                        this.zzbmu.zza(equals2, float1);
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    zzakb.zzb("Fail to parse float", (Throwable)ex);
                }
                final float float1 = 0.0f;
                continue;
            }
        }
    }
}

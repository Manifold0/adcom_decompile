// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;
import java.lang.ref.WeakReference;
import com.google.android.gms.ads.internal.gmsg.zzv;

public final class zzpe implements zzv<Object>
{
    private final String zzaae;
    private final WeakReference<zzpa> zzyg;
    
    public zzpe(final zzpa zzpa, final String zzaae) {
        this.zzyg = new WeakReference<zzpa>(zzpa);
        this.zzaae = zzaae;
    }
    
    @Override
    public final void zza(Object o, final Map<String, String> map) {
        final String s = map.get("ads_id");
        o = map.get("eventName");
        if (!TextUtils.isEmpty((CharSequence)s) && this.zzaae.equals(s)) {
            while (true) {
                try {
                    Integer.parseInt(map.get("eventType"));
                    if ("_ai".equals(o)) {
                        o = this.zzyg.get();
                        if (o != null) {
                            ((zzpa)o).zzbr();
                        }
                        return;
                    }
                }
                catch (Exception ex) {
                    zzakb.zzb("Parse Scion log event type error", (Throwable)ex);
                    continue;
                }
                break;
            }
            if ("_ac".equals(o)) {
                final zzpa zzpa = this.zzyg.get();
                if (zzpa != null) {
                    zzpa.zzbs();
                }
            }
        }
    }
}

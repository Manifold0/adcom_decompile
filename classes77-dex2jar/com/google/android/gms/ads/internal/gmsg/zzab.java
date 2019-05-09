// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import android.content.Context;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zzab implements zzv<Object>
{
    private final Context zzrt;
    
    public zzab(final Context zzrt) {
        this.zzrt = zzrt;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        if (!zzbv.zzfh().zzs(this.zzrt)) {
            return;
        }
        zzbv.zzfh().zza(this.zzrt, map.get("eventName"), map.get("eventId"));
    }
}

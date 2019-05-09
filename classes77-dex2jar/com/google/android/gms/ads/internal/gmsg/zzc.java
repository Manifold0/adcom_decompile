// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzakb;
import java.util.Map;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zzc implements zzv<Object>
{
    private final zzd zzblm;
    
    public zzc(final zzd zzblm) {
        this.zzblm = zzblm;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        final String s = map.get("name");
        if (s == null) {
            zzakb.zzdk("App event with no name parameter.");
            return;
        }
        this.zzblm.onAppEvent(s, map.get("info"));
    }
}
